// https://vuex.vuejs.org/en/actions.html

import { apiServer } from '../setup-axios'

export default {
  login ({ commit }, credentials) {
    return apiServer
      .post('/authenticate', credentials)
      .then(({ data }) => {
        commit('SET_USER_DATA', data)
      })
  },
  logout ({ commit }) {
    commit('LOGOUT')
  },
  setDokumenOrganisasi (context, payload) {
     context.commit('SET_DOKUMEN_ORGANISASI', payload)
  },
  setPenelitian (context, payload) {
    context.commit('SET_PENELITIAN_DATA', payload)
  },
  setRekapPenelitian (context, payload) {
    context.commit('SET_REKAP_PENELITIAN', payload)
  },
  setRiwayatReview (context, payload) {
    context.commit('SET_RIWAYAT_REVIEW', payload)
  },
  setOrganisasi (context, payload) {
    context.commit('SET_ORGANISASI', payload)
  },
  setTokenPddikti (context, payload) {
    context.commit('SET_TOKEN_PDDIKTI', payload)
  },
  setBerita (context, payload) {
    context.commit('SET_BERITA', payload)
  },
  setStatus (context, payload) {
    context.commit('SET_STATUS', payload)
  },

}
