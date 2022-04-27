import axios from 'axios'
import authHeader from './auth-header';

const API_URL = 'http://localhost:8090/api/editor';

class NoteService {
    getAllNotesById(id) {
        return axios.get(API_URL + `/notes/${id}`, {headers: authHeader() });
    }

    createNote(data) {
        return axios.post(API_URL, data, { headers: authHeader() });
    }

    deleteNote(title) {
        return axios.delete(API_URL + `/${title}`, { headers: authHeader() });
    }

    updateNote(id, data) {
        return axios.put(API_URL + `/${id}`, data, { headers: authHeader() }); 
    }
}

export default new NoteService();