package com.david.graficas.ui.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.david.graficas.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.BubbleChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.BubbleData;
import com.github.mikephil.charting.data.BubbleDataSet;
import com.github.mikephil.charting.data.BubbleEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

/**
 * A placeholder fragment containing a simple view.
 */
public class PlaceholderFragment extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";

    private PageViewModel pageViewModel;

    public static PlaceholderFragment newInstance(int index) {
        PlaceholderFragment fragment = new PlaceholderFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_SECTION_NUMBER, index);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pageViewModel = ViewModelProviders.of(this).get(PageViewModel.class);
        int index = 1;
        if (getArguments() != null) {
            index = getArguments().getInt(ARG_SECTION_NUMBER);
        }
        pageViewModel.setIndex(index);
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);
        final TextView textView = root.findViewById(R.id.section_label);
        pageViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        /*
        BarChart barchart = (BarChart) root.findViewById(R.id.graficBarchart);
        // ENTRADA PARA LA GRÁFICA
        ArrayList<BarEntry> barEntry = new ArrayList<BarEntry>();
        barEntry.add(new BarEntry(1, 5));
        barEntry.add(new BarEntry(2, 8));
        barEntry.add(new BarEntry(3, 7));
        barEntry.add(new BarEntry(4, 2));
        // DEFINIMOS EL DATA SET
        BarDataSet dataset = new BarDataSet(barEntry, "PROYECTOS");
        dataset.setColors(ColorTemplate.COLORFUL_COLORS);
        // DEFINIR UN BARDATA (ALIMENTACIÓN DE LA GRÁFICA)
        BarData data = new BarData(dataset);

        barchart.setData(data);
        barchart.getDescription().setText("Descripción de la gráfica");
         */


        BubbleChart bubble_chart = (BubbleChart) root.findViewById(R.id.graficaBubblechart);
        ArrayList<BubbleEntry> bubbleentry = new ArrayList<>();
        bubbleentry.add(new BubbleEntry(1,5,1));
        bubbleentry.add(new BubbleEntry(2,2,0.6f));
        bubbleentry.add(new BubbleEntry(3,8,1.5f));
        bubbleentry.add(new BubbleEntry(4,4,0.3f));
        bubbleentry.add(new BubbleEntry(5,7,0.9f));

        BubbleDataSet dataSet = new BubbleDataSet(bubbleentry, "PROYECTOS");
        dataSet.setColors(ColorTemplate.PASTEL_COLORS);
        BubbleData data1 = new BubbleData(dataSet);

        bubble_chart.setData(data1);
        bubble_chart.getDescription().setText("Descripción");
        XAxis x = bubble_chart.getXAxis();
        x.setAxisMaximum(6);
        x.setAxisMinimum(6);

        return root;
    }
}