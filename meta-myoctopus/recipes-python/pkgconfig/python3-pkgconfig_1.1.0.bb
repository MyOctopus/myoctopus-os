SUMMARY = "pkgconfig is a Python module to interface with the pkg-config command line tool and supports Python 2.6+."
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=faa7f82be8f220bff6156be4790344fc"

DEPENDS = "python3-distribute-native"

SRC_URI = "https://pypi.python.org/packages/source/p/pkgconfig/pkgconfig-${PV}.tar.gz"
SRC_URI[md5sum] = "15998b487c15c5954feb79443b5a937a"
SRC_URI[sha256sum] = "709daaf077aa2b33bedac12706373412c3683576a43013bbaa529fc2769d80df"

S = "${WORKDIR}/pkgconfig-${PV}"

require recipes-python/python/python-dist.inc
