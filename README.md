# Inject ARM-64

Inject ARM-64 is a Virtual-Space based Non-root Cheat injection project, developed by [Nepmods](https://github.com/nepmods).

## Table of Contents

- [Overview](#overview)
- [Features](#features)
- [Installation](#installation)
- [Usage](#usage)
- [Contributing](#contributing)
- [Credits](#credits)
- [License](#license)
- [Contact](#contact)

## Overview

Inject ARM-64 is designed to facilitate the injection of cheats into ARM-64 architecture devices without the need for root access. By utilizing a virtual space, this tool ensures that cheats can be applied safely and effectively, providing an enhanced user experience for developers and gamers alike.

## Features

- **Non-root injection:** No root access is required to inject cheats.
- **Virtual-space based:** Utilizes a virtual environment to ensure safe and effective cheat injection.
- **ARM-64 support:** Specifically designed for ARM-64 architecture devices.
- **User-friendly interface:** Easy to use with a simple and intuitive interface.
- **Dynamic memory allocation:** Efficient use of memory to optimize performance.
- **Secure:** Minimizes risks associated with cheat injections.

## Limitations

- **Android Version:** Only supports Android 8-12
- **ABI** Only supports ARM 64bit (arm64-v8a)

## Todo
- **Upgrade for higher Android:** Support for android 13, 14
- **Support for other abi:** Support for armeabi-v7a, x86, x86_64

# Known issue

- In ```BActivityThread``` at function ```handleBindApplication``` at line ```368```, Code ```application = BRLoadedApk.get(loadedApk).makeApplication(false, null);``` returns `null` at android 13, 14 that causes app to crash.


## Requirements

- ***Android Studio***
- ***NDK as configured in build files***

- ***SDK as configured in build files***

## Installation


1. Clone the repository:
    ```sh
    git clone https://github.com/NepMods/InjectARM64
    ```
2. Open In android studio
3. Sync Project
4. Build Project
5. Customize

## Usage

1. Launch the application
2. Install game apk AS configured
3. Follow the on-screen instructions to inject cheats into your desired application.

## Contributing

We welcome contributions from the community! If you would like to contribute to Inject ARM-64, please follow these steps:

1. Fork the repository.
2. Create a new branch:
    ```sh
    git checkout -b feature-branch
    ```
3. Make your changes and commit them:
    ```sh
    git commit -m "Description of your changes"
    ```
4. Push to your branch:
    ```sh
    git push origin feature-branch
    ```
5. Open a pull request detailing your changes.

## Credits

This project uses various open-source libraries and resources. We would like to acknowledge and thank the developers of these projects:

- [BCore](https://github.com/FBlackBox/BlackBox)
- [Pine](https://github.com/canyie/pine)
- [LGL mod menu 3.2](https://github.com/LGLTeam/Android-Mod-Menu)


## License

Inject ARM-64 is licensed under the MIT License. See the [LICENSE](LICENSE) file for more information.

## Contact

If you have any questions or need further assistance, feel free to reach out:

- **Developer:** Nepmods
- **Email:** [nepmods@proton.me](mailto:nepmods@proton.me)
- **GitHub:** [Nepmods](https://github.com/nepmods)
- **Telegram:** [Telegram](https://t.me/CheaterNeverDies)


---

Thank you for using Inject ARM-64! We hope you find it helpful and look forward to your contributions.
