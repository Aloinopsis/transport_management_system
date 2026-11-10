import api from './api'

export const kontrahentService = {
  getAll() {
    return api.get('/kontrahenci')
  },

  getById(id) {
    return api.get(`/kontrahenci/${id}`)
  },

  create(data) {
    return api.post('/kontrahenci', data)
  },

  update(id, data) {
    return api.put(`/kontrahenci/${id}`, data)
  },

  delete(id) {
    return api.delete(`/kontrahenci/${id}`)
  },
}
