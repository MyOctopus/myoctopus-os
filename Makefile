SHELL = /bin/bash

YOCTO_VER = 1.8
PYTHON_VER = 3.4

MACHINE ?= edison

YOCTO_NAME = poky-fido-13.0.0
YOCTO_DIR = $(YOCTO_NAME)

.os-fetch-stamp:
	wget -c http://downloads.yoctoproject.org/releases/yocto/yocto-$(YOCTO_VER)/$(YOCTO_NAME).tar.bz2
	tar xjf $(YOCTO_NAME).tar.bz2
	ln -sf /usr/bin/python2 $(YOCTO_DIR)/bitbake/bin/python
	# some hacks due to broken python support in yocto
	#patch -p1 -d $(YOCTO_DIR) < distro/poky-dizzy-12.0.0-python.patch
	touch .os-fetch-stamp

.os-edison-stamp: .os-fetch-stamp
	cd $(YOCTO_DIR); git clone https://github.com/koenkooi/meta-edison.git
	touch .os-edison-stamp

.os-myoctopus-layer-stamp: .os-edison-stamp
	cp -uav meta-myoctopus $(YOCTO_DIR)
	touch .os-myoctopus-layer-stamp

.os-myoctopus-init-stamp: .os-myoctopus-layer-stamp
	cd $(YOCTO_DIR); \
	PATH=$$(pwd)/bitbake/bin:$$PATH \
		TEMPLATECONF=meta-myoctopus/conf \
		source oe-init-build-env build-myoctopus
	touch .os-myoctopus-init-stamp

all: .os-myoctopus-init-stamp
	cd $(YOCTO_DIR)/build-myoctopus; \
	PATH=$$(pwd)/../bitbake/bin:$$PATH \
		MACHINE=$(MACHINE) BB_ENV_EXTRAWHITE=MACHINE \
		bitbake core-image-minimal-edison
	mkdir -p dist
	ln -sf ../$(YOCTO_DIR)/build-myoctopus/tmp/deploy/images dist

clean:
	rm -rf .os-*-stamp

