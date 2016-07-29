package com.example.chaiy.popularmovies.helper;

/**
 * Created by Chaiy on 7/9/2016.
 */
public class Constants {

    public static final class HTTP {

        public static final String BASE_URL = "http://api.themoviedb.org/3/";
        public static final String IMAGE_BASE_URL = "http://image.tmdb.org/t/p/";
        public static final String YOUTUBE_IMAGE_BASE_URL = "http://img.youtube.com/vi/";
    }

    public static final class REQUEST_PARAMS {

        public static final String API_KEY = "api_key";
        public static final String NO_OF_PAGES = "page";

    }

    public static final class IMAGE_PARAMS {

        public static final String POSTER_WIDTH_185 = "w185";

        public static final String YOUTUBE_THUMB_NUM_1 = "/0.jpg";
        public static final String YOUTUBE_THUMB_NUM_2 = "/2.jpg";
        public static final String YOUTUBE_THUMB_NUM_3 = "/3.jpg";
        public static final String YOUTUBE_THUMB_NUM_4 = "/4.jpg";

    }

    public static final class SHARED_PREFERENCES {

        public static final String PREFERENCE_NAME = "POPULAR_MOVIES";
        public static final String PREFERENCE_SORT_ORDER = "sort_order";

    }

    public static final class APPLICATION_CONSTANTS {

        public static final String BUNDLE_MOVIE_DETAILS = "BUNDLE_MOVIE_DETAILS";
        public static final String DETAILS_FRAGMENT = "DetailsFragment";
        public static final String SELECTED_POSITION = "selected_position";

        public static final String YOUTUBE_APP_PATH = "vnd.youtube:";
        public static final String YOUTUBE_BASE_URL = "http://www.youtube.com/watch?v=";

    }

}
