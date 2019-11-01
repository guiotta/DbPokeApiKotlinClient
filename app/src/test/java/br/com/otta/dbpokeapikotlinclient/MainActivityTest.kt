package br.com.otta.dbpokeapikotlinclient

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.View
import android.widget.ProgressBar
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.runners.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MainActivityTest {
    @Mock
    private lateinit var progressBar: ProgressBar
    @Mock
    private lateinit var activity: Activity
    @Mock
    private lateinit var intent: Intent

    @Test
    fun shouldCorrectlyUpdateVisibilityFromProgressBar() {
        val mainActivity = MainActivity()

        mainActivity.updateProgressBarVisibility(progressBar, View.GONE)
        Mockito.verify(progressBar).visibility = View.GONE
    }

    @Test
    fun shouldCorrectlyStartNewActivity() {
        val mainActivity = MainActivity()

        mainActivity.startActivityWithIntent(activity, intent, "param")

        Mockito.verify(intent).putExtra("url", "param")
        Mockito.verify(activity).startActivity(intent)
    }
}