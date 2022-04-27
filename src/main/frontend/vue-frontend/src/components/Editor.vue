<template>
    <div class="editor">
        <div class="left-section">
            <div class="add-new-note">
                <button @click="createNote" title="Создать новую запись">+ Создать новую запись</button>
            </div>
            <div class="search-note">
                <input type="text" placeholder="Найти">
                <img :src="require('../assets/images/search.png')">
            </div>
            <div class="filter-note">
                    <li>
                        <a>
                            <img :src="require('../assets/images/edit.png')">
                        </a>
                    </li>
                    <li>
                        <a>
                            <img :src="require('../assets/images/dots.png')">
                        </a>
                    </li>
            </div>
            <div class="saved-notes">
                <ul v-for="note in notes" v-bind:key="note.id">
                    <li @click="openNote(note.title, note.content)">
                        <input v-model="note.title" disabled>
                        <input v-model="note.content" disabled>
                    </li>
                </ul>
            </div>
        </div>
        <div class="right-section" id="right-section">
            <div class="editor-functions"></div>
            <div class="make-note">
                <form>
                    <div class="upper-form">
                        <input type="text" name="title" v-model="note.title" placeholder="Название заметки">
                        <button @click="updateNote"><img src="../assets/images/disketter.png" title="Сохранить заметку"></button>
                        <button @click="deleteNote"><img src="../assets/images/deletee.png" title="Удалить заметку"></button>
                    </div>
                    <div class="bottom-form">
                        <textarea name="content" v-model="note.content" placeholder="Заметка" spellcheck="false"></textarea>
                    </div>
                </form>
            </div>
            <div class="note-info">
                <p>Строка: {{ stringCount }}</p>
                <p>Колонка: {{ columnCount }}</p>
                <p>Количество слов: {{ wordCount }}</p>
            </div>
        </div>
    </div>
</template>

<script>
import NoteService from '../services/note.service'
// использовать uuid для notes.user.id и user.id
// import { uuid } from 'vue-uuid'
export default {
    name: 'editor-page',
    el: "#right-section",
    data() {
        return {
            notes: [],
            timer: '',
            note: {
                id: "",
                title: "",
                content: "",
                user: {
                    id: ""
                }
            },
            submitted: false
        }
    },

    created() {
        this.getAllNotesById()
        this.timer = setInterval(this.getAllNotesById, 3000)
    },

    computed: {
        columnCount: function () {
            return this.note.content.length;
        },

        wordCount: function () {
            return this.note.content.match(/\s+/g)?.length || 0;
        },

        stringCount: function() {
            return this.note.content.length ? this.note.content.split(/\r\n|\r|\n/).length : 0;
        }
    },

    methods: {
        // редактировать заметку из списка
        async openNote(title, content) {
            // TODO: сделать бинарный поиск.
            // Может пригодится treeMap, но в таком случае заметки будут отсортированы в натуральном порядке,
            // следовательно есть вопросы к будущему фильтру по заметкам
            for (let i = 0; i < this.notes.length; i++) {
                if (title === this.notes[i].title && content === this.notes[i].content) {
                    this.note.id = this.notes[i].id
                    this.note.title = this.notes[i].title;
                    this.note.content = this.notes[i].content;
                }
            }
        },

        async getAllNotesById() {
            var data = {
                id: this.notes.id,
                title: this.notes.title,
                content: this.notes.content,
                user: {
                    id: this.$store.state.auth.user.id
                }
            };

            NoteService.getAllNotesById(data.user.id)
            .then((response) => {
                this.notes = response.data;
                console.log(response.data)
                this.submitted = true;
            })
            .catch(e => {
                console.log(e);
            });
        },

        // создать новую заметку
        async createNote() {
            var data = {
                title: "Note", 
                content: "Text",
                // user: {
                //     id: '1'
                // }
            };
            NoteService.createNote(data)
            .then(response => {
                this.note.id = response.data.id;
                console.log(response.data);
                this.submitted = true;
            })
            .catch(e => {
                console.log(e);
            });
        },

        // редактировать заметку
        async updateNote() {
            var data = {
                title: this.note.title,
                content: this.note.content,
            };
            NoteService.updateNote(this.note.id, data)
            .then(response => {
                console.log(response.data);
                this.submitted = true;
            })
            .catch(e => {
                console.log(e);
            });
        },

        // удалить заметку
        async deleteNote() {
            NoteService.deleteNote(this.note.title)
            .then(response => {
                console.log(response.data);
                this.submitted = true;
            })
            .catch(e => {
                console.log(e);
            });
        },

        async cancelAutoUpdate() {
            clearInterval(this.timer);
        },
    },

    // beforeMount() {
    //     this.cancelAutoUpdate();
    // }
    beforeUnmount() {
        this.cancelAutoUpdate();
    }
}
</script>

<style scoped>
.editor {
    height: 100vh;
    display: flex;
    flex-wrap: nowrap;
}

.left-section {
    width: 15%;
    height: inherit;
    border-right: 1px solid lightgray;
}

.add-new-note {
    height: 7%;
    border-bottom: 1px solid lightgray;
    display: flex;
    justify-content: center;
    align-items: center;
}

.add-new-note button {
    padding: 10px 25px 10px 25px;
    border: none;
    background-color: #04ac6c;
    border-radius: 3px;
    color: white;
    font-size: 18px;
    font-weight: 300;
}

.add-new-note button:hover {
    opacity: .9;
    transition: .5s;
    cursor: pointer;
}

.search-note {
    display: flex;
    justify-content: center;
    align-items: center;
    border-bottom: 1px solid lightgray;
    padding: 10px 0px 10px 0px;
}

.search-note input {
    width: 80%;
    font-weight: 300;
    color: black;
    margin-right: 5px;
    outline: none;
    font-size: 18px;
    border: none;
    background-color: #f7f9fa;
}

.filter-note {
    height: 3%;
    border-bottom: 1px solid lightgray;
    padding: 0 20px 0 20px;
    display: flex;
    justify-content: space-between;
}

.filter-note li {
    list-style-type: none;
    width: 20px;
    display: flex;
    flex-direction: column;
}

.filter-note li a {
    color: black;
    text-decoration: none;
}

.filter-note li:last-child a {
    margin-left: auto;
}

.saved-notes {
    list-style-type: none;
    background-color: #f1f1f1;
}

.saved-notes ul {
    display: flex;
    flex-direction: column;
    flex-wrap: nowrap;
    padding-left: 0;
}

.saved-notes ul li {
    border-bottom: 1px solid lightgray;
    list-style-type: none;
    padding: 10px 20px 10px 20px;
    background-color: #f1f1f1;
}

.saved-notes ul li:hover {
    cursor: pointer;
}

.saved-notes ul li input {
    border: none;
    color: black;
    margin-bottom: 5px;
}

.saved-notes ul li input:hover {
    cursor: pointer;
}

.saved-notes ul li input:first-child {
    font-size: 18px;
    font-weight: 600;
}

/* .saved-notes ul li h5 {
    margin-bottom: 5px;
    font-weight: 600;
} */

.right-section {
    width: 85%;
    display: flex;
    flex-direction: column;
    flex-wrap: nowrap;
}

.editor-functions {
    height: 4%;
    border-bottom: 1px solid lightgray;
}

.make-note {
    height: 92%;
    border-bottom: 1px solid lightgray;
}

.upper-form {
    padding: 0px 0px 0px 20px;
    border-bottom: 1px solid lightgray;
    display: flex;
}

.upper-form input {
    border: none;
    width: 90%;
    outline: none;
    background-color: #f7f9fa;
    font-family: roboto-light, sans-serif, serif;
    font-size: 20px;
    font-weight: 300;
    margin-right: 5%;
}

.upper-form button {
    width: 5%;
    background-color: #f7f9fa;
    border: none;
    border-left: 1px solid lightgray;
    padding: 10px 20px 10px 20px;
}

.upper-form button:hover {
    cursor: pointer;
}

.bottom-form {
    padding: 20px 20px 20px 20px;
}

.bottom-form textarea {
    width: 100%;
    height: 100%;
    border: none;
    background-color: #f7f9fa;
    outline: none;
    resize: none;
    overflow: hidden;
    font-family: roboto-light, sans-serif, serif;
    font-size: 18px;
}

.note-info {
    height: 4%;
    display: flex;
    align-items: center;
    flex-wrap: nowrap;
    padding: 0 20px 0 20px;
    font-size: 16px;
}

.note-info p:first-child {
    margin-right: 40px;
}

.note-info p:last-child {
    margin-left: auto;
    margin-right: 50px;
}

</style>
