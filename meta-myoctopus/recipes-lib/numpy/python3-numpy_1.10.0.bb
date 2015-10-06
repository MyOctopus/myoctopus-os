# this requires: FORTRAN_forcevariable = ",fortran" in layer.conf
SUMMARY = "A sophisticated Numeric Processing Package for Python"
SECTION = "devel/python"
LICENSE = "PSF"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=9f4e88b5748e8313caaf33d081ce65a3"
PR = "r0"
BBCLASSEXTEND = "native"

SRC_URI = "${SOURCEFORGE_MIRROR}/numpy/numpy-${PV}.tar.gz \
    file://numpy-type-eval.patch \
    file://no-host-paths.patch \
    ${CONFIGFILESURI} "

CONFIGFILESURI ?= ""

CONFIGFILESURI_aarch64 = " \
    file://config.h \
    file://_numpyconfig.h \
"
CONFIGFILESURI_arm = " \
    file://config.h \
    file://numpyconfig.h \
"
CONFIGFILESURI_armeb = " \
    file://config.h \
    file://numpyconfig.h \
"
CONFIGFILESURI_mipsel = " \
    file://config.h \
    file://numpyconfig.h \
"
CONFIGFILESURI_i586 = " \
    file://config.h \
    file://numpyconfig.h \
"
CONFIGFILESURI_x86-64 = " \
    file://config.h \
    file://_numpyconfig.h \
"
CONFIGFILESURI_mips = " \
    file://config.h \
    file://_numpyconfig.h \
"
CONFIGFILESURI_powerpc = " \
    file://config.h \
    file://_numpyconfig.h \
"
CONFIGFILESURI_powerpc64 = " \
    file://config.h \
    file://_numpyconfig.h \
"
CONFIGFILESURI_mips64 = " \
    file://config.h \
    file://_numpyconfig.h \
"
CONFIGFILESURI_mips64n32 = " \
    file://config.h \
    file://_numpyconfig.h \
"

S = "${WORKDIR}/numpy-${PV}"

require recipes-python/python/python-dist.inc

# numpy goes its own way with distutils
DISTUTILS_INSTALL_ARGS = " --install-lib=${D}/${PYTHON_SITEPACKAGES_DIR} --root=/"

# Make the build fail and replace *config.h with proper one
# This is a ugly, ugly hack - Koen
do_compile_prepend_class-target() {
    # FIXME
    export LDFLAGS="-Lbuild/temp.linux-x86_64-3.4"
    export CPPFLAGS="-Ibuild/src.linux-x86_64-3.4/numpy/core/src/umath"

    BUILD_SYS=${BUILD_SYS} HOST_SYS=${HOST_SYS} \
    ${STAGING_BINDIR_NATIVE}/${PYTHON_PN}-native/${PYTHON_PN} setup.py build ${DISTUTILS_BUILD_ARGS} || \
    true
    cp ${WORKDIR}/*config.h ${S}/build/$(ls ${S}/build | grep src)/numpy/core/include/numpy/
}

FILES_${PN}-staticdev += "${PYTHON_SITEPACKAGES_DIR}/numpy/core/lib/*.a"

SRC_URI[md5sum] = "116c65ae392e9b50aad713f42158f32a"
SRC_URI[sha256sum] = "43b00f5d52b374a731444ba2724bfa1debdbc93312a1b9b28e99700498d169b6"

# install what is needed for numpy.test()
RDEPENDS_${PN} = "python3-unittest \
                  python3-difflib \
                  python3-pprint \
                  python3-pickle \
                  python3-shell \
                  python3-doctest \
                  python3-datetime \
                  python3-distutils \
                  python3-misc \
                  python3-mmap \
                  python3-netclient \
                  python3-numbers \
                  python3-pydoc \
                  python3-pkgutil \
                  python3-email \
                  python3-subprocess \
                  python3-compression \
"

