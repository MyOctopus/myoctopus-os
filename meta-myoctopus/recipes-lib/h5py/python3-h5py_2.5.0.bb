LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://licenses/license.txt;md5=af94682ecb88344364c33dd5ad4962b9"

SRC_URI = "https://pypi.python.org/packages/source/h/h5py/h5py-${PV}.tar.gz"
SRC_URI += "file://h5py-no-local-paths.patch"
SRC_URI[md5sum] = "6e4301b5ad5da0d51b0a1e5ac19e3b74"
SRC_URI[sha256sum] = "9833df8a679e108b561670b245bcf9f3a827b10ccb3a5fa1341523852cfac2f6"

DEPENDS = "python3-distribute-native python3-cython-native python3-six python3-pkgconfig hdf5-native"
RDEPENDS_${PN} += " python3-six python3-numpy hdf5"

S = "${WORKDIR}/h5py-${PV}"

require recipes-python/python/python-dist.inc
