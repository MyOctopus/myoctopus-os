# single-version-externally-managed requires --root, --install-lib set in
# distutils3_install function (distutils3.bbclass) prevents us from
# installing in /, but let's use --install-lib once again just in case
DISTUTILS_INSTALL_ARGS = " --install-lib=${D}/${PYTHON_SITEPACKAGES_DIR} --root=/ --single-version-externally-managed"

inherit distutils3
require recipes-python/python/python-set-ver.inc
