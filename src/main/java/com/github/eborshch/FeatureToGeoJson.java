package com.github.eborshch;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Geometry;
import org.geotools.data.simple.SimpleFeatureCollection;
import org.geotools.data.simple.SimpleFeatureIterator;
import org.json.simple.JSONObject;
import org.opengis.feature.simple.SimpleFeature;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;


/**
 * Created with IntelliJ IDEA.
 * <p/>
 * User: Eugene Borshch
 * Date: 9/17/13
 */
public class FeatureToGeoJson {

    /*
   { "type": "FeatureCollection",
 "features": [
   { "type": "Feature",
     "geometry": {"type": "Point", "coordinates": [102.0, 0.5]},
     "properties": {"prop0": "value0"}
     }
    ]
  }
    */

    public String to(SimpleFeatureCollection featureCollection) {
        JSONObject jsonFeatureCollection = buildFeatureCollection(featureCollection);
        return jsonFeatureCollection.toJSONString();

    }


    private JSONObject buildFeature(SimpleFeature simpleFeature) {
        JSONObject obj = new JSONObject();
        obj.put("type", "Feature");
        obj.put("geometry", buildGeometry((Geometry) simpleFeature.getDefaultGeometry()));
        return obj;

    }

    private Object buildGeometry(Geometry geometry) {

        JSONObject obj = new JSONObject();
        obj.put("type", geometry.getGeometryType());

        List<Coordinate> coordinates = Arrays.asList(geometry.getCoordinates());
        obj.put("coordinates", coordinates);

        //TODO properties

        return obj;

    }

    private JSONObject buildFeatureCollection(SimpleFeatureCollection featureCollection) {
        JSONObject obj = new JSONObject();
        obj.put("type", "FeatureCollection");

        List<JSONObject> features = new LinkedList<JSONObject>();
        SimpleFeatureIterator simpleFeatureIterator = featureCollection.features();
        while (simpleFeatureIterator.hasNext()) {
            SimpleFeature simpleFeature = simpleFeatureIterator.next();
            features.add(buildFeature(simpleFeature));
        }

        obj.put("features", features);
        return obj;
    }
}
