package com.example.hikes

import android.annotation.SuppressLint
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.widget.TextView

import com.esri.arcgisruntime.ArcGISRuntimeEnvironment
import com.esri.arcgisruntime.concurrent.ListenableFuture
import com.esri.arcgisruntime.geometry.Point
import com.esri.arcgisruntime.geometry.SpatialReferences
import com.esri.arcgisruntime.mapping.ArcGISMap
import com.esri.arcgisruntime.mapping.BasemapStyle
import com.esri.arcgisruntime.mapping.Viewpoint
import com.esri.arcgisruntime.mapping.view.*
import com.esri.arcgisruntime.symbology.SimpleMarkerSymbol
import com.esri.arcgisruntime.symbology.TextSymbol
import com.example.hikes.databinding.ActivityMainBinding
import java.util.concurrent.ExecutionException
import kotlin.math.roundToInt


class MainActivity : AppCompatActivity() {

    private var graphicsOverlay = GraphicsOverlay()

    private val activityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val mapView: MapView by lazy {
        activityMainBinding.mapView
    }

    private fun setupMap() {

        // create a map with the BasemapStyle streets
        val map = ArcGISMap(BasemapStyle.ARCGIS_TOPOGRAPHIC)

        // set the map to be displayed in the layout's MapView
        mapView.map = map

        // set the viewpoint, Viewpoint(latitude, longitude, scale)
        mapView.setViewpoint(Viewpoint(43.825386, -111.792824
            , 1190000.0))

    }

    private fun addGraphics() {
        // add hike positions to graphics overlay
        val hikePoints = createHikeGraphics()
        graphicsOverlay.graphics.addAll(hikePoints)

        // add hike names to graphics overlay
        val textGraphics = createTextGraphics()
        graphicsOverlay.graphics.addAll(textGraphics)

        // add graphics overlay to the map view's graphics overlay collection
        mapView.graphicsOverlays.add(graphicsOverlay)


    }


    private fun createHikeGraphics(): Array<Graphic> {
        val hike1loc = Point(-111.9902778, 43.78555556, SpatialReferences.getWgs84())
        val hike2loc = Point(-111.7178538, 43.65749903, SpatialReferences.getWgs84())

        val hikeMarker = SimpleMarkerSymbol(SimpleMarkerSymbol.Style.DIAMOND, Color.argb(255, 0, 0, 128), 10.0F)

        val rm: MutableMap<String, Any> = HashMap()
        rm["Name"] = "R Mountain"
        rm["Length"] = "3.4mi"
        rm["Elevation"] = "964ft"

        val cc: MutableMap<String, Any> = HashMap()
        cc["Name"] = "Cress Creek"
        cc["Length"] = "1.3mi"
        cc["Elevation"] = "311ft"

        return arrayOf(
            Graphic(hike2loc, cc, hikeMarker), Graphic(hike1loc, rm, hikeMarker)
        )
    }

    private fun createTextGraphics(): Array<Graphic> {
        // create a point geometry
        val rMountLoc = Point(-111.9902, 43.785, SpatialReferences.getWgs84())
        val ccMountloc = Point(-111.7178538, 43.65749903, SpatialReferences.getWgs84())

        // create text symbols
        val rMountSymbol = TextSymbol(
            20.0F, "R Mountain" , Color.argb(255, 0, 0, 128),
            TextSymbol.HorizontalAlignment.LEFT, TextSymbol.VerticalAlignment.BOTTOM)
        val ccMountSymbol = TextSymbol(
            20.0F, "Cress Creek" , Color.argb(255, 0, 0, 128),
            TextSymbol.HorizontalAlignment.LEFT, TextSymbol.VerticalAlignment.BOTTOM)
        // define graphics from each geometry and symbol
        val rMountGraphic = Graphic(rMountLoc, rMountSymbol)
        val ccMountGraphic = Graphic(ccMountloc, ccMountSymbol)


        //return array
        return arrayOf(rMountGraphic, ccMountGraphic)

    }


    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activityMainBinding.root)

        setApiKeyForApp()

        setupMap()

        addGraphics()

        mapView.onTouchListener = object : DefaultMapViewOnTouchListener(this, mapView) {

            override fun onSingleTapConfirmed(motionEvent: MotionEvent): Boolean {
                // get the point that was tapped on the screen
                val screenPoint =
                    android.graphics.Point(motionEvent.x.roundToInt(), motionEvent.y.roundToInt())

                // identify graphics on the graphics overlay
                val identifyGraphic: ListenableFuture<IdentifyGraphicsOverlayResult> = mapView
                    .identifyGraphicsOverlayAsync(graphicsOverlay, screenPoint, 10.0, false, 2)

                identifyGraphic.addDoneListener {
                    try {
                        val graphicsOverlayResult = identifyGraphic.get()
                        // get the list of graphics returned by identify graphic overlay
                        val graphic =
                            graphicsOverlayResult.graphics
                        // create a map point from that screen point
                        val mapPoint = mapView.screenToLocation(screenPoint)

                        if (!graphic.isEmpty()) {
                            val calloutContent = TextView(applicationContext).apply {
                                setTextColor(Color.BLACK)
                                setSingleLine(false)
                                setLines(3)
                                // display information about hike
                                text = (graphic[1].attributes["Name"].toString() + "\n" + "Length: " + graphic[1].attributes["Length"].toString() + "\n" + "Elevation Gain: " + graphic[1].attributes["Elevation"].toString() + "\n");
                            }

                            // get the callout, set its content and show it and the tapped location
                            mapView.callout.apply {
                                location = mapPoint
                                content = calloutContent
                                show()
                            }

                            // center the map on the tapped location
                            mapView.setViewpointCenterAsync(mapPoint)
                        }
                    } catch (ie: InterruptedException) {
                        ie.printStackTrace()
                    } catch (ie: ExecutionException) {
                        ie.printStackTrace()
                    }
                }

                return super.onSingleTapConfirmed(motionEvent);
            }
        }
    }

    override fun onPause() {
        mapView.pause()
        super.onPause()
    }

    override fun onResume() {
        super.onResume()
        mapView.resume()
    }

    override fun onDestroy() {
        mapView.dispose()
        super.onDestroy()
    }

    private fun setApiKeyForApp(){
        // set your API key
        ArcGISRuntimeEnvironment.setApiKey("AAPK1b154f676bb24ffeb506e3e8ac26a766DDEIhqpTJMiV4iSWYXTuEPRWAKiKxviwF6E29yq7Vh3fXxIyL0pq9x0X-D6dvDwG")

    }
}