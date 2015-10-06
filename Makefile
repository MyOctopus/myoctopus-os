SHELL = /bin/bash

YOCTO_VER = 1.8
PYTHON_VER = 3.4

MACHINE ?= edison

YOCTO_NAME = poky-fido-13.0.0
YOCTO_DIR = $(YOCTO_NAME)

EDISON_IMAGE = edison-image-ww25.5-15

.os-fetch-stamp:
	wget -c http://downloads.yoctoproject.org/releases/yocto/yocto-$(YOCTO_VER)/$(YOCTO_NAME).tar.bz2
	tar xjf $(YOCTO_NAME).tar.bz2
	ln -sf /usr/bin/python2 $(YOCTO_DIR)/bitbake/bin/python
	patch -p1 -d $(YOCTO_DIR) < $(YOCTO_NAME)-python.patch
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

.os-edison-firmware-stamp:
	wget -c http://downloadmirror.intel.com/25028/eng/$(EDISON_IMAGE).zip
	mkdir -p $(EDISON_IMAGE)
	unzip -d $(EDISON_IMAGE) $(EDISON_IMAGE).zip
	touch .os-edison-firmware-stamp

flash: .os-edison-firmware-stamp
	cp dist/images/edison/core-image-minimal-edison-edison.ext4 $(EDISON_IMAGE)/edison-image-edison.ext4 
	cd $(EDISON_IMAGE); sudo ./flashall.sh

clean:
	rm -rf .os-*-stamp

