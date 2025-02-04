# Use this script to setup your local development environment

try:

    import os
    import platform

    if platform.system() == 'Linux':
        
        print('Please note that this script only support Debian based systems.')
        
        print('Linux Setup Initiated')
        os.system('sudo apt update')
        os.system('sudo apt install default-jre')
        os.system('sudo apt install eclipse')
        
        
    elif platform.system() == 'Darwin':
        
        print('MacOS Setup Initiated')
        os.system('/bin/bash -c "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/HEAD/install.sh)"')
        os.system('brew install openjdk')
        print('JRE installed on your Mac. Not added to path.')
        os.system('brew install --cask visual-studio-code')
        print('Please install Java extension for VS Code through the app.')
        
except:
    
    print('ERROR: Unknown Error')
