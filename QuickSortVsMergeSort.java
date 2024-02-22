package org.example.chart;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.geometry.Side;
import javafx.scene.Scene;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.effect.Effect;
import javafx.scene.effect.InnerShadow;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.StrokeType;
import javafx.stage.Stage;
import kotlin.jvm.PurelyImplements;

import java.util.ArrayList;


public class QuickSortVsMergeSort extends Application {
    StackPane pane;
    ScatterChart<Number, Number> sc;
    Scene scene;

    public ArrayList<XYChart.Data<Number, Number>> getData(){
        int[] array = new int[Math.abs(Sort.getRandom(0, (int)Math.pow(10, 6)))];
        for (int i = 0; i < array.length; i++){
            array[i] = Sort.getRandom(5, 1000);
        }
        long start_time = System.currentTimeMillis();
        int[] quick_sorted = Sort.quick_sort(array);
        long end_time = System.currentTimeMillis();
        long elapsed_time_quick_sort = end_time - start_time;
        start_time = System.currentTimeMillis();
        int[] merge_sorted = Sort.merge_sort(array);
        end_time = System.currentTimeMillis();
        long elapsed_time_merge_sort = end_time - start_time;

        ArrayList<XYChart.Data<Number, Number>> data = new ArrayList<XYChart.Data<Number, Number>>();
        data.add(new XYChart.Data<Number, Number>(array.length/1000, elapsed_time_merge_sort));
        data.add(new XYChart.Data<Number, Number>(array.length/1000, elapsed_time_quick_sort));
        return data;
    }


    @Override public void start(Stage stage) {
        stage.setTitle("Quick Sort vs Merge Sort graph");
        pane = new StackPane();
        final NumberAxis xAxis = new NumberAxis(0, 1000, 100);
        final NumberAxis yAxis = new NumberAxis(0, 1000, 100);
        sc = new ScatterChart<Number,Number>(xAxis,yAxis);
        xAxis.setLabel("Length of Array(in thousands)");
        yAxis.setLabel("Runtime(ms)");
        sc.setTitle("Quick Sort vs. Merge Sort Runtime");

        XYChart.Series merge_sort_series = new XYChart.Series();
        merge_sort_series.setName("Merge Sort");

        XYChart.Series quick_sort_series = new XYChart.Series();
        quick_sort_series.setName("Quick Sort");

        for (int i = 0; i < 200; i++){
            ArrayList<XYChart.Data<Number, Number>> data = getData();
            merge_sort_series.getData().add(data.get(0));
            quick_sort_series.getData().add(data.get(1));
        }

        sc.getData().addAll(merge_sort_series, quick_sort_series);
        sc.setLegendSide(Side.TOP);

        pane.getChildren().add(sc);

        scene = new Scene(pane, 670, 534);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);

    }
}