inherit autotools pkgconfig python3native
require recipes-python/python/python-set-ver.inc

FILES_${PN} += "${PYTHON_SITEPACKAGES_DIR}/*.egg ${PYTHON_SITEPACKAGES_DIR}/*.pth"

do_install_append() {
    install -d ${D}/${PYTHON_SITEPACKAGES_DIR}
    # since yocto linux 1.7
    cd ${S}

    STAGING_INCDIR=${STAGING_INCDIR} \
    STAGING_LIBDIR=${STAGING_LIBDIR} \
    PYTHONPATH=${D}${PYTHON_SITEPACKAGES_DIR} \
    BUILD_SYS=${BUILD_SYS} HOST_SYS=${SYS} \
    ${STAGING_BINDIR_NATIVE}/${PYTHON_PN}-native/${PYTHON_PN} setup.py install --install-lib=${D}/${PYTHON_SITEPACKAGES_DIR}

    rm -f ${D}${PYTHON_SITEPACKAGES_DIR}/easy-install.pth
    rm -f ${D}${PYTHON_SITEPACKAGES_DIR}/site.py
    rm -rf ${D}${PYTHON_SITEPACKAGES_DIR}/__pycache__
    echo ./$(ls -d ${D}${PYTHON_SITEPACKAGES_DIR}/*.egg | sed 's/.*-packages\/\(.*\)/\1/') > ${D}${PYTHON_SITEPACKAGES_DIR}/${PN}.pth
}

