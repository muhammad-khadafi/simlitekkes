// https://vuex.vuejs.org/en/mutations.html

import { apiServer } from '../setup-axios'

export default {
  SET_USER_DATA (state, userData) {
    state.user = userData
    localStorage.setItem('user', JSON.stringify(userData))
    apiServer.defaults.headers.common.Authorization = `Bearer ${
      userData.token
      }`
  },
  LOGOUT () {
    localStorage.removeItem('user')
    location.reload()
  },
  SET_DOKUMEN_ORGANISASI (state, payload) {
    state.dokumenOrganisasi.id = payload.id
    state.dokumenOrganisasi.judul = payload.judul
    state.dokumenOrganisasi.idJenisDokumen = payload.idJenisDokumen
    state.dokumenOrganisasi.nomorDokumen = payload.nomorDokumen
    state.dokumenOrganisasi.tahunTerbit = payload.tahunTerbit
    state.dokumenOrganisasi.namaFile = payload.namaFile
    state.dokumenOrganisasi.message = payload.message
    state.dokumenOrganisasi.snackbarColor = payload.snackbarColor
  },
  SET_PENELITIAN_DATA (state, penelitianData) {
    state.penelitian = penelitianData
  },
  SET_REKAP_PENELITIAN (state, payload) {
    state.rekapPenelitian.idSkemaPenelitian = payload.idSkemaPenelitian
    state.rekapPenelitian.tahunUsulan = payload.tahunUsulan
    state.rekapPenelitian.tahunMulaiPelaksanaan = payload.tahunMulaiPelaksanaan
    state.rekapPenelitian.tahunSelesaiPelaksanaan = payload.tahunSelesaiPelaksanaan
  },
  SET_RIWAYAT_REVIEW (state, payload) {
    state.riwayatReview.tahunUsulan = payload.tahunUsulan
    state.riwayatReview.idPeneliti = payload.idPeneliti
  },
  SET_ORGANISASI (state, payload) {
    state.organisasi.snackbarColor = payload.snackbarColor
    state.organisasi.snackbarText = payload.snackbarText
  },
  SET_TOKEN_PDDIKTI (state, payload) {
    state.tokenDikti = payload
  },
  SET_BERITA (state, payload) {
    state.berita.snackbarColor = payload.snackbarColor
    state.berita.snackbarText = payload.snackbarText
  },
  SET_STATUS (state, payload) {
    state.status.snackbarColor = payload.snackbarColor
    state.status.snackbarText = payload.snackbarText
  },
}
