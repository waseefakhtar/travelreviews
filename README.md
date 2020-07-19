# Android Take Home Test from GetYourGuide

This package includes an APK to run on your Android device in _/apk_ directory as well as some screenshots and a GIF from the app in _/asset_ directory.

## How to install APK on your device

1. Send the APK file to your Android device.
2. Install the APK.
3. Run the installed app. (Make sure you're connected to the internet)

## How to sort the review list

1. Click the options menu from the top right.
2. Click a sort option. (Clicking the Date or Rating option loads the data in ascending order initially while clicking it again loads them in descending order)
3. The new data loads in that sort order

## Features of the app

The app shows a list of reviews for the _Berlin Tempelhof Airport: The Legend of Tempelhof_ tour (retrieved from the JSON data here: https://travelers-api.getyourguide.com/activities/23776/reviews).

The action bar shows a number of actions to sort the list:

Sort by Default: Display all reviews as it is ordered in the JSON response by default.
Sort by Date: Displays all reviews sorted by the latest or earliest date.
Sort by Rating: Displays all reviews sorted by HIGH-LOW or LOW-HIGH ratings.

## How to install and run the app from source code

1. Download and install Android Studio 4.0: https://developer.android.com/studio
2. Open Android Studio.
2. Click on open an existing Android Studio project.
3. Select the unzipped travelreviews directory and let the build to be completed.
4. Go to Run > Run 'app' to run on a connected device or on an emulator.
