#!/bin/sh

cd vuecomponent
export GWT=1
npm run build

# TODO: this is ugly... can we run GWT dev mode from a different directory??
sed -ie 's#/js#js#g' dist/index.html
sed -ie 's#/css#css#g' dist/index.html
cp -rv dist/js ../web/src/main/webapp
cp -rv dist/css ../web/src/main/webapp
cp -v dist/index.html ../web/src/main/webapp/parent.html

cd ..
mvn gwt:run -pl web