# Android Chat Application

This is a simple Android chat application that allows users to register, log in, create profiles, search for other users, send messages, and view their chat history. The app is powered by Firebase Realtime Database for storing chat data.

## Features:
- User Registration and Login
- Profile Creation and Update
- Search for Users
- Send and Receive Messages
- View Chat History

## Setup Instructions:
1. Clone the repository to your local machine.
2. Open the project in Android Studio.
3. Add your `google-services.json` file in the `app/` directory. You can obtain this file from your Firebase console after setting up a Firebase project. **This file is required for Firebase authentication and database services to work.**
4. Sync your project with Gradle to download necessary dependencies.
5. Run the app on your Android device or emulator.

## Requirements:
- Android Studio Arctic Fox or later
- A Firebase account with a Realtime Database setup
- Google Firebase `google-services.json` file

## Notes:
- The `google-services.json` file has been removed from this repository for security reasons. You must add your own file to enable Firebase services.
