SUMMARY = "Python 2 and 3 compatibility library"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=6f00d4a50713fa859858dd9abaa35b21"

DEPENDS = "python3-distribute-native"

SRC_URI = "https://pypi.python.org/packages/source/s/six/six-${PV}.tar.gz"
SRC_URI[md5sum] = "476881ef4012262dfc8adc645ee786c4"
SRC_URI[sha256sum] = "e24052411fc4fbd1f672635537c3fc2330d9481b18c0317695b46259512c91d5"

S = "${WORKDIR}/six-${PV}"

require recipes-python/python/python-dist.inc
