// https://vuex.vuejs.org/en/state.html

export default {
  user: null,
  dokumenOrganisasi: {
    id: null,
    judul: null,
    idJenisDokumen: null,
    nomorDokumen: null,
    tahunTerbit: null,
    namaFile: null,
    message: null,
    snackbarColor: null,
  },
  penelitian: null,
  rekapPenelitian: {
      idSkemaPenelitian: null,
      tahunUsulan: null,
      tahunMulaiPelaksanaan: null,
      tahunSelesaiPelaksanaan: null,
  },
  riwayatReview: {
    tahunUsulan: null,
    idPeneliti: null,
  },
  organisasi: {
    snackbarColor: null,
    snackbarText: null,
  },
  berita: {
    snackbarColor: null,
    snackbarText: null,
  },
  status: {
    snackbarColor: null,
    snackbarText: null,
  },
  tokenDikti: null,
}
