# Evergreen

Evergreen is an Android app that reports whether all the components essential to
an Android TV experience are installed, along with the correct latest versions
of each.

Instead of requiring dogfooders & engineers to manually check versions in
multiple places, this app automates the process.

## Installation

### Via Google Assistant

-   Say **“Install Evergreen App”**

-   Locate the one with the icon below, and install it.

    <a href="evergreen.mp4"><img src="evergreen-banner.png" alt="Evergreen Banner" width="320" style="border: 1px solid #cdcdcd; border-radius: 16px"></a>

-   Press the Install button.

## How to Use

<a target="_blank" href="evergreen.mp4">Watch Video</a>

1.  Start the Evergreen app: say “Launch Evergreen” to Assistant.

1.  Look out for any apps in <span style="color:red">RED</span>.

1.  Click on each app that is red, then click: UPDATE.

1.  Repeat for all apps that appear in red.

## For Developers Only

#### How to update the config

The easiest way to update the config is by using the app itself.

-   Launch Evergreen
-   Tools → Print Local Config
-   Open `adb logcat` on a connected computer, copy the JSON, and paste it into
    the location above.

## License

This code & resources are distributed under the [Apache 2 License](LICENSE).

Contains licensed components:

- [MaterialDesignIcons.com](https://materialdesignicons.com/)
- [Community Icons](https://github.com/Templarian/MaterialDesign)
- [SIL Open Font License 1.1](http://scripts.sil.org/cms/scripts/page.php?item_id=OFL_web)
