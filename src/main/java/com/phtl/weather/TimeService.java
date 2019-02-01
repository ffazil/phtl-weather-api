package com.phtl.weather;

import java.time.ZonedDateTime;

public interface TimeService {
    ZonedDateTime timeAtLocation(GeoCoordinate coordinate);
}
