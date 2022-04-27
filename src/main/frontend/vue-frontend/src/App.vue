<template>
    <div id="app">
    <nav class="header">
      <a class="logotype" href="/">
        <img style="margin-right: 3px" :src="require('./assets/images/writing.png')" alt="Note logotype">
        <p>NOTEPAD</p>
      </a>
      <div class="menu">
        <li>
          <router-link to="/home" class="link">Главная</router-link>
        </li>
        <li v-if="showAdminBoard">
          <router-link to="/admin" class="link">Admin Board</router-link>
        </li>
        <li v-if="showModeratorBoard">
          <router-link to="/mod" class="link">Moderator Board</router-link>
        </li>
        <li>
          <router-link v-if="currentUser" to="/editor" class="link">Editor</router-link>
        </li>
      </div>
      <div v-if="!currentUser" class="account">
        <li>
          <router-link to="/register" class="link">Регистрация</router-link>
        </li>
        <li>
          <router-link to="/login" class="link">Вход</router-link>
        </li>
      </div>
      <div v-if="currentUser" class="logout">
        <li>
          <router-link to="/profile" class="link">
            {{ currentUser.username }}
          </router-link>
        </li>
        <li class="logout-button">
          <a class="link" @click="logOut">Выйти</a>
        </li>
      </div>
    </nav>
    <div class="main">
      <router-view />
    </div>
    <div>

    </div>
  </div>
</template>

<script>
export default {
  computed: {
    currentUser() {
      return this.$store.state.auth.user;
    },

    showAdminBoard() {
      if (this.currentUser && this.currentUser['roles']) {
        return this.currentUser['roles'].includes('ROLE_ADMIN');
      }
      return false;
    },

    showModeratorBoard() {
      if (this.currentUser && this.currentUser['roles']) {
        return this.currentUser['roles'].includes('ROLE_MODERATOR');
      }
      return false;
    },

    showEditorBoard() {
      return this.$store.state.auth.user;
    }

  },

  methods: {
    logOut() {
      this.$store.dispatch('auth/logout');
      this.$router.push('/login');
    }
  }
};
</script>

<style scoped>

@font-face {
  font-family: roboto-light;
  src: url('./assets/fonts/Roboto-Light.ttf');
}

* {
  margin: 0;
  padding: 0;
}

#app {
  font-family: roboto-light, sans-serif, serif;
  background-color: #f7f9fa;
}

.header {
  display: flex;
  background-color: black;
  justify-content: space-between;
  align-items: center;
  padding: 10px 20px 10px 20px;
}

.logotype {
  display: flex;
  align-items: center;
  justify-content: space-between;
  text-decoration: none;
  color: white;
  font-size: 18px;
  font-family: roboto-light,sans-serif, sans-serif;
  font-weight: 800;
}

.menu {
  width: 30%;
  display: flex;
  padding: 10px 20px 10px 20px;
  justify-content: space-between;
}

.menu li {
  list-style-type: none;
}

.menu li .link:hover {
    text-decoration: none;
}

.link {
  color: white;
}

.account {
  padding: 10px 0px 10px 0px;
  display: flex;
  align-items: center;
}

.account li {
  list-style-type: none;
  margin-right: 50px;
}

.account li:last-child {
  margin-right: 0;
}

.account .link {
  border: none;
  background-color: #04ac6c;
  padding: 10px 20px 10px 20px;
  border-radius: 3px;
  opacity: .95;
}

.account .link:hover {
  text-decoration: none;
  opacity: 1;
  transition: .5s;
}

.logout {
  padding: 10px 0px 10px 0px;
  display: flex;
  align-items: center;
}

.logout li {
  list-style-type: none;
  margin-right: 50px;
}

.logout li:last-child {
  margin-right: 0;
}

.logout .link {
  border: none;
  background-color: #04ac6c;
  padding: 10px 20px 10px 20px;
  border-radius: 3px;
  opacity: .95;
}

.logout .link:hover {
  text-decoration: none;
  opacity: 1;
  transition: .5s;
}

.logout-button:hover {
  cursor: pointer;
}
</style>
