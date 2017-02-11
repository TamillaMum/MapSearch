package com.example.tamillich.mapsearch.module;

/**
 * Created by TamilliCH on 2/11/2017.
 */

public class Const {

    // константы для интент поиска (экшены)
    public static final String ACTION_FIND_BY_RADIUS = "Find by Radius";
    public static final String ACTION_FIND_NEAR_BY_PLACE = "Find by Place";

    // константы экстра для интента
    public static final String EXTRA_CURRENT_COORDINATE = "COORDINATE";
    public static final String EXTRA_SEARCH_QUERY = "Text Query";

    // костанта апиай гугла

    public  static final String GOOGLE_API = "&key=AIzaSyAU0MKEpguoob72Yg7tbcW08o-pevt8z-I";

    // запрос по Апиай гугла
    //query=bank&location=32.0662,34.7778&radius=10000&key=AIzaSyAU0MKEpguoob72Yg7tbcW08o-pevt8z-I

    public static final String SEARCH_BY_PLACE = "https://maps.googleapis.com/maps/api/place/textsearch/json?query=";
    public static final String MY_LOCATION = "&location=";
    public static final String RADIUS = "&radius=5000";

    //константы результатов запроса

    public static final String RESULT_JSON = "Json";

}
