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
            , 2000000.0))

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
        val hike3loc = Point(-111.584847,43.71025798, SpatialReferences.getWgs84())
        val hike4loc = Point(-111.629959, 43.6457475, SpatialReferences.getWgs84())
        val hike5loc = Point(-111.33833, 43.77083, SpatialReferences.getWgs84())
        val hike6loc = Point(-111.1217377, 43.4343979, SpatialReferences.getWgs84())
        val hike7loc = Point(-111.1599426, 43.430475, SpatialReferences.getWgs84())
        val hike8loc = Point(-113.9569876, 44.89805017, SpatialReferences.getWgs84())
        val hike9loc = Point(-111.920776, 41.226144, SpatialReferences.getWgs84())
        val hike10loc = Point(-111.4366406, 44.3293007, SpatialReferences.getWgs84())
        val hike11loc = Point(-111.403623, 44.6415117, SpatialReferences.getWgs84())
        val hike12loc = Point(-111.215, 43.39566667, SpatialReferences.getWgs84())
        val hike13loc = Point(-116.2760125, 47.8029711, SpatialReferences.getWgs84())
        val hike14loc = Point(-110.6555556,42.85, SpatialReferences.getWgs84())
        val hike15loc = Point(-118.751289, 36.612564, SpatialReferences.getWgs84())
        val hike16loc = Point(-116.1842915, 45.1426709, SpatialReferences.getWgs84())
        val hike17loc = Point(-112.3952537, 42.7543585, SpatialReferences.getWgs84())
        val hike18loc = Point(-111.2410588, 44.518256, SpatialReferences.getWgs84())
        val hike19loc = Point(-114.479698, 42.59919, SpatialReferences.getWgs84())
        val hike20loc = Point(-111.6241667, 43.61111111, SpatialReferences.getWgs84())

        val hikeMarker = SimpleMarkerSymbol(SimpleMarkerSymbol.Style.DIAMOND, Color.argb(255, 0, 0, 128), 10.0F)

        val rm: MutableMap<String, Any> = HashMap()
        rm["Name"] = "R Mountain"
        rm["Length"] = "3.4mi"
        rm["Elevation"] = "964ft"

        val cc: MutableMap<String, Any> = HashMap()
        cc["Name"] = "Cress Creek"
        cc["Length"] = "1.3mi"
        cc["Elevation"] = "311ft"

        val wd: MutableMap<String, Any> = HashMap()
        wd["Name"] = "Webster Dam"
        wd["Length"] = "2.7mi"
        wd["Elevation"] = "587ft"

        val kc: MutableMap<String, Any> = HashMap()
        kc["Name"] = "Kelly Canyon"
        kc["Length"] = "3.7mi"
        kc["Elevation"] = "613ft"

        val pl: MutableMap<String, Any> = HashMap()
        pl["Name"] = "Packsaddle Lake"
        pl["Length"] = "5.2mi"
        pl["Elevation"] = "1072ft"

        val up: MutableMap<String, Any> = HashMap()
        up["Name"] = "Upper Palisades"
        up["Length"] = "15mi"
        up["Elevation"] = "1679ft"

        val lp: MutableMap<String, Any> = HashMap()
        lp["Name"] = "Lower Palisades"
        lp["Length"] = "9.9mi"
        lp["Elevation"] = "1095ft"

        val gb: MutableMap<String, Any> = HashMap()
        gb["Name"] = "Gold Bug"
        gb["Length"] = "3.5mi"
        gb["Elevation"] = "958ft"

        val hv: MutableMap<String, Any> = HashMap()
        hv["Name"] = "Hidden Valley"
        hv["Length"] = "7.4mi"
        hv["Elevation"] = "941ft"

        val mf: MutableMap<String, Any> = HashMap()
        mf["Name"] = "Mesa Falls"
        mf["Length"] = "2mi"
        mf["Elevation"] = "91ft"

        val hl: MutableMap<String, Any> = HashMap()
        hl["Name"] = "Henry's Lake"
        hl["Length"] = "3.2mi"
        hl["Elevation"] = "187ft"

        val wc: MutableMap<String, Any> = HashMap()
        wc["Name"] = "Waterfall Canyon"
        wc["Length"] = "23.1mi"
        wc["Elevation"] = "4143ft"

        val ec: MutableMap<String, Any> = HashMap()
        ec["Name"] = "Little Elk Creek"
        ec["Length"] = "6.2mi"
        ec["Elevation"] = "2608ft"

        val ls: MutableMap<String, Any> = HashMap()
        ls["Name"] = "Lower Sheep Creek Canyon"
        ls["Length"] = "1.2mi"
        ls["Elevation"] = "104ft"

        val dcc: MutableMap<String, Any> = HashMap()
        dcc["Name"] = "Deep Creek Crest"
        dcc["Length"] = "5.6mi"
        dcc["Elevation"] = "1597ft"

        val ck: MutableMap<String, Any> = HashMap()
        ck["Name"] = "Corral Creek"
        ck["Length"] = "6.5mi"
        ck["Elevation"] = "761ft"

        val cs: MutableMap<String, Any> = HashMap()
        cs["Name"] = "Cherry Springs"
        cs["Length"] = "2mi"
        cs["Elevation"] = "137ft"

        val bc: MutableMap<String, Any> = HashMap()
        bc["Name"] = "Black Canyon"
        bc["Length"] = "12.6mi"
        bc["Elevation"] = "2532ft"

        val sf: MutableMap<String, Any> = HashMap()
        sf["Name"] = "South Fork Snake"
        sf["Length"] = "14.2mi"
        sf["Elevation"] = "3497ft"

        val ss: MutableMap<String, Any> = HashMap()
        ss["Name"] = "Stinking Springs"
        ss["Length"] = "4.6mi"
        ss["Elevation"] = "793ft"

        return arrayOf(
            Graphic(hike2loc, cc, hikeMarker),
            Graphic(hike1loc, rm, hikeMarker),
            Graphic(hike3loc, wd, hikeMarker),
            Graphic(hike4loc, kc, hikeMarker),
            Graphic(hike5loc, pl, hikeMarker),
            Graphic(hike6loc, up, hikeMarker),
            Graphic(hike7loc, lp, hikeMarker),
            Graphic(hike8loc, gb, hikeMarker),
            Graphic(hike9loc, hv, hikeMarker),
            Graphic(hike10loc, mf, hikeMarker),
            Graphic(hike11loc, hl, hikeMarker),
            Graphic(hike12loc, wc, hikeMarker),
            Graphic(hike13loc, ec, hikeMarker),
            Graphic(hike14loc, ls, hikeMarker),
            Graphic(hike15loc, dcc, hikeMarker),
            Graphic(hike16loc, ck, hikeMarker),
            Graphic(hike17loc, cs, hikeMarker),
            Graphic(hike18loc, bc, hikeMarker),
            Graphic(hike19loc, sf, hikeMarker),
            Graphic(hike20loc, ss, hikeMarker)
        )
    }

    private fun createTextGraphics(): Array<Graphic> {
        // create a point geometry
        val rMountLoc = Point(-111.9902, 43.785, SpatialReferences.getWgs84())
        val ccMountLoc = Point(-111.7178538, 43.65749903, SpatialReferences.getWgs84())
        val wdMountLoc = Point(-111.584847,43.71025798, SpatialReferences.getWgs84())
        val kcMountLoc = Point(-111.629959, 43.6457475, SpatialReferences.getWgs84() )
        val plMountLoc = Point(-111.33833, 43.77083, SpatialReferences.getWgs84())
        val upMountLoc = Point(-111.1217377, 43.4343979, SpatialReferences.getWgs84())
        val lpMountLoc = Point(-111.1599426, 43.430475, SpatialReferences.getWgs84())
        val gbMountLoc = Point(-113.9569876, 44.89805017, SpatialReferences.getWgs84())
        val hvMountLoc = Point(-111.920776, 41.226144, SpatialReferences.getWgs84())
        val mfMountLoc = Point(-111.4366406, 44.3293007, SpatialReferences.getWgs84())
        val hlMountLoc = Point(-111.403623, 44.6415117, SpatialReferences.getWgs84())
        val wcMountLoc = Point(-111.215, 43.39566667, SpatialReferences.getWgs84())
        val ecMountLoc = Point(-116.2760125, 47.8029711, SpatialReferences.getWgs84())
        val lsMountLoc = Point(-110.6555556,42.85, SpatialReferences.getWgs84())
        val dccMountLoc = Point(-118.751289, 36.612564, SpatialReferences.getWgs84())
        val ckMountLoc = Point(-116.1842915, 45.1426709, SpatialReferences.getWgs84())
        val csMountLoc = Point(-112.3952537, 42.7543585, SpatialReferences.getWgs84())
        val bcMountLoc = Point(-111.2410588, 44.518256, SpatialReferences.getWgs84())
        val sfMountLoc = Point(-114.479698, 42.59919, SpatialReferences.getWgs84())
        val ssMountLoc = Point(-111.6241667, 43.61111111, SpatialReferences.getWgs84())


        // create text symbols
        val rMountSymbol = TextSymbol(
            15.0F, "R Mountain" , Color.argb(255, 0, 0, 128),
            TextSymbol.HorizontalAlignment.LEFT, TextSymbol.VerticalAlignment.BOTTOM)
        val ccMountSymbol = TextSymbol(
            15.0F, "Cress Creek" , Color.argb(255, 0, 0, 128),
            TextSymbol.HorizontalAlignment.LEFT, TextSymbol.VerticalAlignment.BOTTOM)
        // define graphics from each geometry and symbol
        val wdMountSymbol = TextSymbol(
            15.0F, "Webster Dam" , Color.argb(255, 0, 0, 128),
            TextSymbol.HorizontalAlignment.LEFT, TextSymbol.VerticalAlignment.BOTTOM)
        val kcMountSymbol = TextSymbol(
            15.0F, "Kelly Canyon" , Color.argb(255, 0, 0, 128),
            TextSymbol.HorizontalAlignment.LEFT, TextSymbol.VerticalAlignment.BOTTOM)
        val plMountSymbol = TextSymbol(
            15.0F, "Packsaddle Lake" , Color.argb(255, 0, 0, 128),
            TextSymbol.HorizontalAlignment.LEFT, TextSymbol.VerticalAlignment.BOTTOM)
        val upMountSymbol = TextSymbol(
            15.0F, "Upper Palisades" , Color.argb(255, 0, 0, 128),
            TextSymbol.HorizontalAlignment.LEFT, TextSymbol.VerticalAlignment.BOTTOM)
        val lpMountSymbol = TextSymbol(
            15.0F, "Lower Palisades" , Color.argb(255, 0, 0, 128),
            TextSymbol.HorizontalAlignment.LEFT, TextSymbol.VerticalAlignment.BOTTOM)
        val gbMountSymbol = TextSymbol(
            15.0F, "Gold Bug" , Color.argb(255, 0, 0, 128),
            TextSymbol.HorizontalAlignment.LEFT, TextSymbol.VerticalAlignment.BOTTOM)
        val hvMountSymbol = TextSymbol(
            15.0F, "Hidden Valley" , Color.argb(255, 0, 0, 128),
            TextSymbol.HorizontalAlignment.LEFT, TextSymbol.VerticalAlignment.BOTTOM)
        val mfMountSymbol = TextSymbol(
            15.0F, "Mesa Falls" , Color.argb(255, 0, 0, 128),
            TextSymbol.HorizontalAlignment.LEFT, TextSymbol.VerticalAlignment.BOTTOM)
        val hlMountSymbol = TextSymbol(
            15.0F, "Henry's Lake" , Color.argb(255, 0, 0, 128),
            TextSymbol.HorizontalAlignment.LEFT, TextSymbol.VerticalAlignment.BOTTOM)
        val wcMountSymbol = TextSymbol(
            15.0F, "Waterfall Canyon" , Color.argb(255, 0, 0, 128),
            TextSymbol.HorizontalAlignment.LEFT, TextSymbol.VerticalAlignment.BOTTOM)
        val ecMountSymbol = TextSymbol(
            15.0F, "Little Elk Creek" , Color.argb(255, 0, 0, 128),
            TextSymbol.HorizontalAlignment.LEFT, TextSymbol.VerticalAlignment.BOTTOM)
        val lsMountSymbol = TextSymbol(
            15.0F, "Lower Sheep Creek Canyon" , Color.argb(255, 0, 0, 128),
            TextSymbol.HorizontalAlignment.LEFT, TextSymbol.VerticalAlignment.BOTTOM)
        val dccMountSymbol = TextSymbol(
            15.0F, "Deep Creek Crest" , Color.argb(255, 0, 0, 128),
            TextSymbol.HorizontalAlignment.LEFT, TextSymbol.VerticalAlignment.BOTTOM)
        val ckMountSymbol = TextSymbol(
            15.0F, "Corral Creek" , Color.argb(255, 0, 0, 128),
            TextSymbol.HorizontalAlignment.LEFT, TextSymbol.VerticalAlignment.BOTTOM)
        val csMountSymbol = TextSymbol(
            15.0F, "Cherry Springs" , Color.argb(255, 0, 0, 128),
            TextSymbol.HorizontalAlignment.LEFT, TextSymbol.VerticalAlignment.BOTTOM)
        val bcMountSymbol = TextSymbol(
            15.0F, "Black Canyon" , Color.argb(255, 0, 0, 128),
            TextSymbol.HorizontalAlignment.LEFT, TextSymbol.VerticalAlignment.BOTTOM)
        val sfMountSymbol = TextSymbol(
            15.0F, "South Fork Snake" , Color.argb(255, 0, 0, 128),
            TextSymbol.HorizontalAlignment.LEFT, TextSymbol.VerticalAlignment.BOTTOM)
        val ssMountSymbol = TextSymbol(
            15.0F, "Stinking Springs" , Color.argb(255, 0, 0, 128),
            TextSymbol.HorizontalAlignment.LEFT, TextSymbol.VerticalAlignment.BOTTOM)



        val rMountGraphic = Graphic(rMountLoc, rMountSymbol)
        val ccMountGraphic = Graphic(ccMountLoc, ccMountSymbol)
        val wdMountGraphic = Graphic(wdMountLoc, wdMountSymbol)
        val kcMountGraphic = Graphic(kcMountLoc, kcMountSymbol)
        val plMountGraphic = Graphic(plMountLoc, plMountSymbol)
        val upMountGraphic = Graphic(upMountLoc, upMountSymbol)
        val lpMountGraphic = Graphic(lpMountLoc, lpMountSymbol)
        val gbMountGraphic = Graphic(gbMountLoc, gbMountSymbol)
        val hvMountGraphic = Graphic(hvMountLoc, hvMountSymbol)
        val mfMountGraphic = Graphic(mfMountLoc, mfMountSymbol)
        val hlMountGraphic = Graphic(hlMountLoc, hlMountSymbol)
        val wcMountGraphic = Graphic(wcMountLoc, wcMountSymbol)
        val ecMountGraphic = Graphic(ecMountLoc, ecMountSymbol)
        val lsMountGraphic = Graphic(lsMountLoc, lsMountSymbol)
        val dccMountGraphic = Graphic(dccMountLoc, dccMountSymbol)
        val ckMountGraphic = Graphic(ckMountLoc, ckMountSymbol)
        val csMountGraphic = Graphic(csMountLoc, csMountSymbol)
        val bcMountGraphic = Graphic(bcMountLoc, bcMountSymbol)
        val sfMountGraphic = Graphic(sfMountLoc, sfMountSymbol)
        val ssMountGraphic = Graphic(ssMountLoc, ssMountSymbol)

        //return array
        return arrayOf(rMountGraphic, ccMountGraphic, wdMountGraphic, kcMountGraphic
            ,plMountGraphic, upMountGraphic, lpMountGraphic, gbMountGraphic, hvMountGraphic
            ,mfMountGraphic, hlMountGraphic, wcMountGraphic, ecMountGraphic, lsMountGraphic
            ,dccMountGraphic ,ckMountGraphic, csMountGraphic, bcMountGraphic, sfMountGraphic
            ,ssMountGraphic)

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