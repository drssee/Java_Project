package util;

import service.MovieService;
import service.MovieServiceImpl;

public enum MovieServiceUtil {
    INSTANCE;
    public MovieService movieService;
    MovieServiceUtil(){
        movieService = new MovieServiceImpl();
    }
}
