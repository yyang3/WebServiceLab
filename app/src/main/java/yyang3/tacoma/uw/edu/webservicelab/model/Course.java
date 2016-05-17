package yyang3.tacoma.uw.edu.webservicelab.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * new class should be used
 * a.	Modify the Course class to add member variables (String type) for courseId, shortDescription,
 * longDescription, prereqs. Use m as the prefix for member variables.
 * b.	Create the following constants in the class. These values must match the json names that we use on the web service.
 * c.	Create a constructor that takes all the parameters specified in (a) as well as  getters and setters.
 * d.	Make the Course class implement Serializable. This allows us to the pass the object as a parameter.
 * e.	Add a method to parse json String as shown below.
 */
public class Course implements Serializable{

    private String mcourseId;
    private String mshortDescription;
    private String mlongDescription;
    private String mprereqs;

    public static final String ID = "id", SHORT_DESC = "shortDesc"
            , LONG_DESC = "longDesc", PRE_REQS = "prereqs";

    public Course(String mcourseId, String mshortDescription, String mlongDescription, String mprereqs) {
        this.mcourseId = mcourseId;
        this.mshortDescription = mshortDescription;
        this.mlongDescription = mlongDescription;
        this.mprereqs = mprereqs;
    }

    public String getMcourseId() {
        return mcourseId;
    }

    public void setMcourseId(String mcourseId) {
        if (mcourseId == null || mcourseId.length() < 5) {
            throw new IllegalArgumentException();
        } else {
            this.mcourseId = mcourseId;
        }

    }

    public String getMshortDescription() {
        return mshortDescription;
    }

    public void setMshortDescription(String mshortDescription) {
        this.mshortDescription = mshortDescription;
    }

    public String getMlongDescription() {
        return mlongDescription;
    }

    public void setMlongDescription(String mlongDescription) {
        this.mlongDescription = mlongDescription;
    }

    public String getMprereqs() {
        return mprereqs;
    }

    public void setMprereqs(String mprereqs) {
        this.mprereqs = mprereqs;
    }

    /**
     * Parses the json string, returns an error message if unsuccessful.
     * Returns course list if success.
     * @param courseJSON
     * @return reason or null if successful.
     */
    public static String parseCourseJSON(String courseJSON, List<Course> courseList) {
        String reason = null;
        if (courseJSON != null) {
            try {
                JSONArray arr = new JSONArray(courseJSON);

                for (int i = 0; i < arr.length(); i++) {
                    JSONObject obj = arr.getJSONObject(i);
                    Course course = new Course(obj.getString(Course.ID), obj.getString(Course.SHORT_DESC)
                            , obj.getString(Course.LONG_DESC), obj.getString(Course.PRE_REQS));
                    courseList.add(course);
                }
            } catch (JSONException e) {
                reason =  "Unable to parse data, Reason: " + e.getMessage();
            }

        }
        return reason;
    }

}
