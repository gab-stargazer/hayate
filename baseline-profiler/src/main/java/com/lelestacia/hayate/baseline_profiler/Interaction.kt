package com.lelestacia.hayate.baseline_profiler

import androidx.benchmark.macro.MacrobenchmarkScope
import androidx.test.uiautomator.By
import androidx.test.uiautomator.BySelector
import androidx.test.uiautomator.Until

fun MacrobenchmarkScope.interaction() {
    pressHome()
    startActivityAndWait()

    //  Wait for initialization to finish
    val textLoadingSelector = By.res("text:loading-message")
    device.wait(Until.gone(textLoadingSelector), 5000)

    //  Navigate on Exploration Screen
    val iconButtonSearchSelector: BySelector = By.res("button:search")
    device.wait(Until.hasObject(iconButtonSearchSelector), 5000)
    device.findObject(iconButtonSearchSelector).click()
    device.waitForIdle()

    val popularAnimeSelector = By.res("anime:5114")
    device.wait(Until.hasObject(popularAnimeSelector), 5000)
    device.findObject(popularAnimeSelector).click()
    device.wait(Until.hasObject(By.text("Information")), 5000)
    device.pressBack()
    device.wait(Until.hasObject(popularAnimeSelector), 5000)

    device.findObject(By.res("button:schedule")).click()
    val scheduleAnimeSelector = By.res("anime:8687")
    device.wait(Until.hasObject(scheduleAnimeSelector), 5000)
    device.findObject(scheduleAnimeSelector).click()
    device.wait(Until.hasObject(By.text("Information")), 5000)
    device.pressBack()
    device.wait(Until.hasObject(scheduleAnimeSelector), 5000)

    device.findObject(By.res("button:airing")).click()
    val airingAnimeSelector = By.res("anime:55813")
    device.wait(Until.hasObject(airingAnimeSelector), 5000)
    device.findObject(airingAnimeSelector).click()
    device.wait(Until.hasObject(By.text("Information")), 5000)
    device.pressBack()
    device.wait(Until.hasObject(airingAnimeSelector), 5000)

    device.findObject(By.res("button:upcoming")).click()
    val upcomingAnimeSelector = By.res("anime:49458")
    device.wait(Until.hasObject(upcomingAnimeSelector), 5000)
    device.findObject(upcomingAnimeSelector).click()
    device.wait(Until.hasObject(By.text("Information")), 5000)
    device.pressBack()
    device.wait(Until.hasObject(upcomingAnimeSelector), 5000)

    device.findObject(By.res("button:collection")).click()
    device.wait(Until.hasObject(upcomingAnimeSelector), 5000)
    device.findObject(By.res("button:more")).click()
    device.wait(Until.hasObject(By.text("Application Information")), 5000)
    device.findObject(By.text("Application Information")).click()
    device.wait(Until.hasObject(By.text("Repository")), 5000)
    device.pressBack()


//            Halt this for now
//            device.findObject(By.res("button:filter:type"))
//            val tvFilterTextSelector = By.res("dropdown:item:Tv")
//            device.wait(Until.hasObject(tvFilterTextSelector), 5000)
//            device.findObject(tvFilterTextSelector).click()
//            device.wait(Until.gone(textLoadingSelector), 5000)
//
//            device.findObject(By.res("button:filter:filter")).click()
//            val byPopularityFilterSelector = By.res("dropdown:item:bypopularity")
//            device.wait(Until.hasObject(byPopularityFilterSelector), 5000)
//            device.findObject(byPopularityFilterSelector).click()
//            device.wait(Until.gone(textLoadingSelector), 5000)
//
//            device.findObject(By.res("button:filter:rating"))
//            val r17RatingSelector = By.res("dropdown:item:r17")
//            device.wait(Until.hasObject(r17RatingSelector), 5000)
//            device.findObject(r17RatingSelector).click()
//            device.wait(Until.gone(textLoadingSelector), 5000)

    // TODO Write more interactions to optimize advanced journeys of your app.
    // For example:
    // 1. Wait until the content is asynchronously loaded
    // 2. Scroll the feed content
    // 3. Navigate to detail screen

    // Check UiAutomator documentation for more information how to interact with the app.
    // https://d.android.com/training/testing/other-components/ui-automator
}