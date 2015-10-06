DESCRIPTION = "Cython is a language specially designed for writing Python extension modules. \
It's designed to bridge the gap between the nice, high-level, easy-to-use world of Python \
and the messy, low-level world of C."
SECTION = "devel/python"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=e23fadd6ceef8c618fc1c65191d846fa"
SRCNAME = "Cython"
BBCLASSEXTEND = "native"

DEPENDS = "python3-distribute-native"

SRC_URI = "http://www.cython.org/release/${SRCNAME}-${PV}.tar.gz"
S = "${WORKDIR}/${SRCNAME}-${PV}"

require recipes-python/python/python-dist.inc

SRC_URI[md5sum] = "188e18c826dae4514ee0628144df6c3e"
SRC_URI[sha256sum] = "85a8713db65d9ad3e7e2f01e6ac424d4ae2a40349f1391b2b0f494ccb7e0fda7"

DISTUTILS_INSTALL_ARGS += "--skip-build"
RDEPENDS_${PN} += "\
    python3-distribute-native \
    python-netserver \
    python-subprocess \
    python-shell \
"

RDEPENDS_${PN}_class-native = ""
