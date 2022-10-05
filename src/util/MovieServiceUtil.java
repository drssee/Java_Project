package util;

import service.MovieService;
import service.MovieServiceImpl;

public enum MovieServiceUtil implements Util {
    INSTANCE;
    public MovieService movieService;
    MovieServiceUtil(){
        movieService = new MovieServiceImpl();
    }
}
