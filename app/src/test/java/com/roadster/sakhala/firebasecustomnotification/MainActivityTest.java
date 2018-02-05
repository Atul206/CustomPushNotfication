package com.roadster.sakhala.firebasecustomnotification;

import android.widget.TextView;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

/**
 * Created by atulsakhala on 02/02/18.
 */
@RunWith(MockitoJUnitRunner.class)
public class MainActivityTest {


    @Mock
    MainActivity activity;

    TextView textView;


    @Before
    public void setUp() throws Exception {
        textView = (TextView) activity.findViewById(R.id.value);
    }


    @Test
    public void isActivityNullTest() {
        //MainActivity ma = new MainActivity();
        when(textView.getText().toString()).thenReturn("Hello World");
        assertThat(textView.getText().toString(), is("Hello World"));
    }

    @After
    public void tearDown() throws Exception {
        activity = null;
    }

}