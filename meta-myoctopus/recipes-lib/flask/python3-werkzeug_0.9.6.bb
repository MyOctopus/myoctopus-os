SUMMARY = "WSGI utility library for Python"
HOMEPAGE = "http://werkzeug.pocoo.org/"
SECTION = "libs"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://LICENSE;md5=ad2e600a437b1b03d25b02df8c23ad1c"

DEPENDS = "python3-distribute-native"

SRC_URI = "https://pypi.python.org/packages/source/W/Werkzeug/Werkzeug-${PV}.tar.gz"
SRC_URI[md5sum] = "f7afcadc03b0f2267bdc156c34586043"
SRC_URI[sha256sum] = "7f11e7e2e73eb22677cac1b11113eb6106f66cedef13d140e83cf6563c90b79c"

S = "${WORKDIR}/Werkzeug-${PV}"

require recipes-python/python/python-dist.inc
