package edu.upenn.cis350.gpx;

import static org.junit.Assert.*;

import org.junit.Test;

import java.util.ArrayList;

import java.util.Date;

/**
 * Class to test GPXcalculator. Written by Peter Kutz.
 */
public class GPXcalculatorTest {
	
	/**
	 * Tests whether the total distance traveled is being computed correctly.
	 */
	@Test
	public void testDistanceTraveled() {
		GPXtrkpt point1 = new GPXtrkpt(-10.0, 0.0, new Date());
		GPXtrkpt point2 = new GPXtrkpt(0.0, 0.0, new Date());
		GPXtrkpt point3 = new GPXtrkpt(0.0, 10.0, new Date());
		ArrayList<GPXtrkpt> pointList1 = new ArrayList<GPXtrkpt>();
		pointList1.add(point1);
		pointList1.add(point2);
		ArrayList<GPXtrkpt> pointList2 = new ArrayList<GPXtrkpt>();
		pointList2.add(point2);
		pointList2.add(point3);
		GPXtrkseg segment1 = new GPXtrkseg(pointList1);
		GPXtrkseg segment2 = new GPXtrkseg(pointList2);
		ArrayList<GPXtrkseg> segments = new ArrayList<GPXtrkseg>();
		segments.add(segment1);
		segments.add(segment2);
		GPXtrk track = new GPXtrk("My Track", segments);
		double distanceTraveled = GPXcalculator.calculateDistanceTraveled(track);
		assertEquals(20.0, distanceTraveled, 0.0);
	}

	/**
	 * Tests whether the distance traveled is set to -1 when the track is null.
	 */
	@Test
	public void testNullTrack() {
		GPXtrk track = null;
		double distanceTraveled = GPXcalculator.calculateDistanceTraveled(track);
		assertEquals(-1.0, distanceTraveled, 0.0);
	}
	
	/**
	 * Tests whether the distance traveled is set to -1 when there are no segments in the track.
	 */
	@Test
	public void testNoTrackSegment() {
		ArrayList<GPXtrkseg> segments = new ArrayList<GPXtrkseg>();
		GPXtrk track = new GPXtrk("My Track", segments);
		double distanceTraveled = GPXcalculator.calculateDistanceTraveled(track);
		assertEquals(-1.0, distanceTraveled, 0.0);
	}
	
	/**
	 * Tests whether the distance traveled is set to 0 when there are null segments in the track.
	 */
	@Test
	public void testNullTrackSegment() {
		ArrayList<GPXtrkseg> segments = new ArrayList<GPXtrkseg>();
		segments.add(null);
		GPXtrk track = new GPXtrk("My Track", segments);
		double distanceTraveled = GPXcalculator.calculateDistanceTraveled(track);
		assertEquals(0.0, distanceTraveled, 0.0);
	}
	
	/**
	 * Tests whether the distance traveled is set to 0 when a segment doesn't have any points.
	 */
	@Test
	public void testNoTrackPoint() {
		ArrayList<GPXtrkseg> segments = new ArrayList<GPXtrkseg>();
		ArrayList<GPXtrkpt> points = new ArrayList<GPXtrkpt>();
		GPXtrkseg segment = new GPXtrkseg(points);
		segments.add(segment);
		GPXtrk track = new GPXtrk("My Track", segments);
		double distanceTraveled = GPXcalculator.calculateDistanceTraveled(track);
		assertEquals(0.0, distanceTraveled, 0.0);
	}
	
	/**
	 * Tests whether the distance traveled is set to 0 when a segment has only one point.
	 */
	@Test
	public void testOneTrackPoint() {
		ArrayList<GPXtrkseg> segments = new ArrayList<GPXtrkseg>();
		ArrayList<GPXtrkpt> points = new ArrayList<GPXtrkpt>();
		GPXtrkpt point = new GPXtrkpt(0.0, 0.0, new Date());
		points.add(point);
		GPXtrkseg segment = new GPXtrkseg(points);
		segments.add(segment);
		GPXtrk track = new GPXtrk("My Track", segments);
		double distanceTraveled = GPXcalculator.calculateDistanceTraveled(track);
		assertEquals(0.0, distanceTraveled, 0.0);
	}
	
	/**
	 * Tests whether the distance traveled is set to 0 when a segment has a null point.
	 */
	@Test
	public void testNullTrackPoint() {
		ArrayList<GPXtrkseg> segments = new ArrayList<GPXtrkseg>();
		ArrayList<GPXtrkpt> points = new ArrayList<GPXtrkpt>();
		points.add(null);
		GPXtrkseg segment = new GPXtrkseg(points);
		segments.add(segment);
		GPXtrk track = new GPXtrk("My Track", segments);
		double distanceTraveled = GPXcalculator.calculateDistanceTraveled(track);
		assertEquals(0.0, distanceTraveled, 0.0);
	}
	
	/**
	 * Tests whether the distance traveled is set to 0 when the latitude is less than -90.
	 */
	@Test
	public void testLatitudeTooLow() {
		ArrayList<GPXtrkseg> segments = new ArrayList<GPXtrkseg>();
		ArrayList<GPXtrkpt> points = new ArrayList<GPXtrkpt>();
		GPXtrkpt point = new GPXtrkpt(-95.0, 0.0, new Date());
		points.add(point);
		GPXtrkseg segment = new GPXtrkseg(points);
		segments.add(segment);
		GPXtrk track = new GPXtrk("My Track", segments);
		double distanceTraveled = GPXcalculator.calculateDistanceTraveled(track);
		assertEquals(0.0, distanceTraveled, 0.0);
	}
	
	/**
	 * Tests whether the distance traveled is set to 0 when the latitude is greater than 90.
	 */
	@Test
	public void testLatitudeTooHigh() {
		ArrayList<GPXtrkseg> segments = new ArrayList<GPXtrkseg>();
		ArrayList<GPXtrkpt> points = new ArrayList<GPXtrkpt>();
		GPXtrkpt point = new GPXtrkpt(95.0, 0.0, new Date());
		points.add(point);
		GPXtrkseg segment = new GPXtrkseg(points);
		segments.add(segment);
		GPXtrk track = new GPXtrk("My Track", segments);
		double distanceTraveled = GPXcalculator.calculateDistanceTraveled(track);
		assertEquals(0.0, distanceTraveled, 0.0);
	}
	
	/**
	 * Tests whether the distance traveled is set to 0 when the longitude is less than -180.
	 */
	@Test
	public void testLongitudeTooLow() {
		ArrayList<GPXtrkseg> segments = new ArrayList<GPXtrkseg>();
		ArrayList<GPXtrkpt> points = new ArrayList<GPXtrkpt>();
		GPXtrkpt point = new GPXtrkpt(0.0, -185.0, new Date());
		points.add(point);
		GPXtrkseg segment = new GPXtrkseg(points);
		segments.add(segment);
		GPXtrk track = new GPXtrk("My Track", segments);
		double distanceTraveled = GPXcalculator.calculateDistanceTraveled(track);
		assertEquals(0.0, distanceTraveled, 0.0);
	}
	
	/**
	 * Tests whether the distance traveled is set to 0 when the longitude is greater than 180.
	 */
	@Test
	public void testLongitudeTooHigh() {
		ArrayList<GPXtrkseg> segments = new ArrayList<GPXtrkseg>();
		ArrayList<GPXtrkpt> points = new ArrayList<GPXtrkpt>();
		GPXtrkpt point = new GPXtrkpt(0.0, 185.0, new Date());
		points.add(point);
		GPXtrkseg segment = new GPXtrkseg(points);
		segments.add(segment);
		GPXtrk track = new GPXtrk("My Track", segments);
		double distanceTraveled = GPXcalculator.calculateDistanceTraveled(track);
		assertEquals(0.0, distanceTraveled, 0.0);
	}
	
}
