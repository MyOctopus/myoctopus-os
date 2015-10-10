SUMMARY = "Microframework for Python based on Werkzeug, Jinja 2 and good intentions"
HOMEPAGE = "http://flask.pocoo.org/"
SECTION = "libs"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://LICENSE;md5=79aa8b7bc4f781210d6b5c06d6424cb0"

DEPENDS = "python3-distribute-native"
RDEPENDS_python3-flask_append = "\
    python3-werkzeug \
    python3-itsdangerous \
    python3-markupsafe \
    python3-jinja2"

SRC_URI = "http://pypi.python.org/packages/source/F/Flask/Flask-${PV}.tar.gz"
SRC_URI[md5sum] = "378670fe456957eb3c27ddaef60b2b24"
SRC_URI[sha256sum] = "4c83829ff83d408b5e1d4995472265411d2c414112298f2eb4b359d9e4563373"

S = "${WORKDIR}/Flask-${PV}"

require recipes-python/python/python-dist.inc

