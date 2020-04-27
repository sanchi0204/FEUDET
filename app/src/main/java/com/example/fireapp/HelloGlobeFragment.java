package com.example.fireapp;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mousebird.maply.ComponentObject;
import com.mousebird.maply.GlobeMapFragment;
import com.mousebird.maply.MaplyBaseController;
import com.mousebird.maply.Point2d;
import com.mousebird.maply.QuadImageTileLayer;
import com.mousebird.maply.RemoteTileInfo;
import com.mousebird.maply.RemoteTileSource;
import com.mousebird.maply.Shape;
import com.mousebird.maply.ShapeInfo;
import com.mousebird.maply.ShapeSphere;
import com.mousebird.maply.SphericalMercatorCoordSystem;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class HelloGlobeFragment extends GlobeMapFragment

{   Context context;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle inState) {
        super.onCreateView(inflater, container, inState);

        // Do app specific setup logic.
//        container.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(context,Home.class));
//            }
//        });


        return baseControl.getContentView();
    }

    @Override
    protected MapDisplayType chooseDisplayType() {
        return MapDisplayType.Globe;
    }

    @Override
    protected void controlHasStarted() {
        // setup base layer tiles
        String cacheDirName = "stamen_watercolor";
        File cacheDir = new File(getActivity().getCacheDir(), cacheDirName);
        cacheDir.mkdir();
        RemoteTileSource remoteTileSource = new RemoteTileSource(new RemoteTileInfo("http://tile.stamen.com/watercolor/", "png", 0, 18));
        remoteTileSource.setCacheDir(cacheDir);
        SphericalMercatorCoordSystem coordSystem = new SphericalMercatorCoordSystem();

        // globeControl is the controller when using MapDisplayType.Globe
        // mapControl is the controller when using MapDisplayType.Map
        QuadImageTileLayer baseLayer = new QuadImageTileLayer(globeControl, coordSystem, remoteTileSource);
        baseLayer.setImageDepth(1);
        baseLayer.setSingleLevelLoading(false);
        baseLayer.setUseTargetZoomLevel(false);
        baseLayer.setCoverPoles(true);
        baseLayer.setHandleEdges(true);

        // add layer and position
        globeControl.addLayer(baseLayer);
        globeControl.animatePositionGeo(-3.6704803, 40.5023056, 5, 1.0);

        insertSpheres();
    }

    private void insertSpheres() {

        List<Shape> shapes = new ArrayList<>();

        // Kansas City
        ShapeSphere shape = new ShapeSphere();


        // Washington D.C.
        shape = new ShapeSphere();
        shape.setLoc(Point2d.FromDegrees(77.4988, 28.7522));
        shape.setRadius(0.09f);
        shapes.add(shape);

        // McMurdo Station
        shape = new ShapeSphere();
        shape.setLoc(Point2d.FromDegrees(77.4988, 28.7522));
        shape.setRadius(0.09f);
        shapes.add(shape);

        // Windhoek
        shape = new ShapeSphere();
        shape.setLoc(Point2d.FromDegrees(77.4988, 28.7522));
        shape.setRadius(0.09f);
        shapes.add(shape);


        ShapeInfo shapeInfo = new ShapeInfo();
        shapeInfo.setColor(1.0f, 0.0f, 0.0f, 0.0f); // R,G,B,A - values [0.0 => 1.0]
        shapeInfo.setDrawPriority(1000000);

        ComponentObject componentObject = globeControl.addShapes(shapes, shapeInfo, MaplyBaseController.ThreadMode.ThreadAny);

    }

//    @Override
//    public void onClick(View view) {
//
//        Intent intent = new Intent(HelloGlobeFragment.this, ));
//        startActivity(intent);
//
//    }
}
