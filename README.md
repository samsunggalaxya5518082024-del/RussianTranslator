# Russian Translator for Android

A powerful Android application that translates English text to Russian in real-time across all installed apps using the Accessibility Service API.

## Features

✅ **Real-time Translation** - Automatically translates English words to Russian as you interact with apps  
✅ **Universal Coverage** - Works across all installed applications  
✅ **Easy Toggle** - Enable/disable translation with a simple switch  
✅ **Lightweight** - Minimal battery and memory usage  
✅ **Extensive Dictionary** - 150+ common English-Russian word translations  
✅ **User-Friendly Interface** - Simple and intuitive settings  
✅ **Android 16+** - Compatible with modern Android devices  

## How It Works

The app uses Android's **Accessibility Service** API to:
1. Monitor text appearing on the screen across all applications
2. Identify English words in the text
3. Replace them with Russian translations in real-time

## Installation

### Requirements
- Android 16.0 or higher
- Accessibility Service permission

### Setup Instructions

1. **Install the App**
   - Clone this repository
   - Build and install using Android Studio or command line:
     ```bash
     ./gradlew installDebug
     ```

2. **Enable Accessibility Service**
   - Open the app
   - Tap "Enable Accessibility Service" button
   - Navigate to Settings > Accessibility
   - Find "Russian Translator Service" and enable it
   - Confirm the permission prompt

3. **Activate Translation**
   - Return to the app
   - Toggle the "Toggle Translator" switch to ON
   - Translation is now active in all apps!

## Project Structure

```
RussianTranslator/
├── app/
│   ├── src/
│   │   ├── main/
│   │   │   ├── AndroidManifest.xml          # App manifest with service definition
│   │   │   ├── kotlin/
│   │   │   │   └── com/example/russiantranslator/
│   │   │   │       ├── MainActivity.kt       # Main UI activity
│   │   │   │       ├── TranslatorAccessibilityService.kt  # Core translation service
│   │   │   │       └── TranslationDictionary.kt  # English-Russian dictionary
│   │   │   └── res/
│   │   │       ├── layout/
│   │   │       │   └── activity_main.xml    # Main UI layout
│   │   │       ├── values/
│   │   │       │   ├── strings.xml          # String resources
│   │   │       │   └── themes.xml           # Color and theme definitions
│   │   │       └── xml/
│   │   │           └── accessibility_service_config.xml  # Service configuration
│   ├── build.gradle.kts                     # App build configuration
│   └── proguard-rules.pro                   # Code obfuscation rules
├── build.gradle.kts                         # Root build configuration
├── settings.gradle.kts                      # Gradle settings
└── README.md                                # This file
```

## Usage

### Enabling Translation
1. Open the Russian Translator app
2. Tap the "Enable Accessibility Service" button if not already enabled
3. Toggle the switch to turn translation ON
4. The status will show "Translation is active in all apps"

### Disabling Translation
1. Simply toggle the switch OFF in the app
2. Translation will immediately stop

### Supported Words

The built-in dictionary includes:
- Common greetings: hello, hi, bye, goodbye
- Basic responses: yes, no, ok, thanks, sorry
- Actions: save, delete, edit, cancel, confirm
- UI elements: menu, button, settings, home, back
- Status indicators: loading, error, success, online, offline
- And many more...

## Technical Details

### Accessibility Service Integration
- Implements `AccessibilityService` for system-wide text monitoring
- Processes accessibility events: `TYPE_WINDOW_STATE_CHANGED`, `TYPE_WINDOW_CONTENT_CHANGED`, `TYPE_VIEW_TEXT_CHANGED`
- Recursively traverses accessibility node tree to find and translate text
- Runs efficiently with minimal overhead

### Dictionary System
- Dictionary stored as a Kotlin `Map` for O(1) lookup time
- Word matching with regex pattern `[a-zA-Z]+`
- Preserves text structure and punctuation
- Case-insensitive translation with original formatting

### Permissions
The app requires:
- `android.permission.ACCESSIBILITY_EVENTS` - To receive accessibility events
- Accessibility Service binding permission (system-level)

## Building and Testing

### Debug Build
```bash
./gradlew assembleDebug
./gradlew installDebug
```

### Release Build
```bash
./gradlew assembleRelease
./gradlew installRelease
```

### Testing
```bash
./gradlew connectedAndroidTest
```

## Performance Considerations

- **Event Processing**: Limited to 100ms notification timeout for battery efficiency
- **Dictionary Lookup**: O(1) time complexity using HashMap
- **Memory Usage**: Minimal - only processes visible UI elements
- **CPU Impact**: Negligible - only processes on screen changes

## Troubleshooting

### Translation not working?
1. ✓ Verify Accessibility Service is enabled in Settings > Accessibility
2. ✓ Check the app toggle is ON
3. ✓ Restart the app
4. ✓ Ensure the target app isn't blocking accessibility

### Service keeps stopping?
1. ✓ Go to Settings > Apps > Russian Translator
2. ✓ Ensure "Battery optimization" is disabled
3. ✓ Enable "Protect from optimization" if available

### Words not translating?
1. ✓ Check if the word is in the dictionary
2. ✓ Verify the word is in English (a-z, A-Z only)
3. ✓ Some apps may render text as images (OCR required)

## Future Enhancements

🔲 Add OCR support for text in images  
🔲 Customizable user dictionary  
🔲 Multiple language pair support  
🔲 Statistics and translation history  
🔲 Real-time API-based translation (Yandex Translate API)  
🔲 Exclude list for specific apps  
🔲 Advanced text processing  

## Security & Privacy

- ✅ No data sent to external servers
- ✅ All translation done locally on device
- ✅ No sensitive data collection
- ✅ No ads or tracking
- ✅ Open source - review the code!

## Contributing

Contributions are welcome! Please see [CONTRIBUTING.md](CONTRIBUTING.md) for details.

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## Support

For issues, bugs, or feature requests, please create an issue on GitHub.

## Author

Created with ❤️ for Android developers and Russian language learners.

---

**Note**: This app requires Accessibility Service permission for full functionality. This is a standard Android feature used for assistive technologies. Always review permissions before granting them.