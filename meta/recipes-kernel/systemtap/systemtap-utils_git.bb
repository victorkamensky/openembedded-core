SUMMARY = "Small set only runtime utilities for SystemTap"
HOMEPAGE = "https://sourceware.org/systemtap/"

require systemtap_git.inc

FILESEXTRAPATHS =. "${FILE_DIRNAME}/systemtap:"

DEPENDS = "elfutils"

# should not be used along with complete systemtap
RCONFLICTS_${PN} = "systemtap"

EXTRA_OECONF += "--with-libelf=${STAGING_DIR_TARGET} --without-rpm \
            --without-nss --without-avahi --without-dyninst \
            --disable-server --disable-grapher --enable-prologues \
            --with-python3 --without-python2-probes \
            --disable-sdt-probes --disable-translator \
            --disable-libvirt --disable-sqlite --disable-monitor \
            --without-python3-probes \
            --disable-docs --disable-publican --disable-refdocs \
            ac_cv_prog_have_javac=no \
            ac_cv_prog_have_jar=no "

do_install_append () {
            rm -rf ${D}${datadir}/systemtap
            rm -rf ${D}${includedir}
            # remove bash dependency we don't need it anyway
            rm ${D}${libexecdir}/systemtap/stap-env
}

inherit autotools gettext
