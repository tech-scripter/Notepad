<template>
     <div class="main">
    <div class="form">
      <h3>Вход</h3>
      <Form @submit="handleLogin" :validation-schema="schema">
        <div class="input">
          <Field name="username" type="text" class="form-control" placeholder="Никнейм" />
          <ErrorMessage name="username" class="error-message" />
        </div>
        <div class="input">
          <Field name="password" type="password" class="form-control" placeholder="Пароль" />
          <ErrorMessage name="password" class="error-message" />
        </div>
        <div class="input">
          <button class="btn btn-primary btn-block" :disabled="loading">
            <span
              v-show="loading"
              class="spinner-border spinner-border-sm"
            ></span>
            <span>Войти</span>
          </button>
        </div>
        <div class="input">
          <div v-if="message" class="alert alert-danger" role="alert">
            {{ message }}
          </div>
        </div>
      </Form>
    </div>
  </div>
</template>

<script>
import { Form, Field, ErrorMessage } from 'vee-validate';
import * as yup from 'yup';

export default {
    name: "Login-page",
    components: {
        Form,
        Field,
        ErrorMessage
    },

    data() {
        const schema = yup.object().shape({
            username: yup.string().required("Никнейм обязателен!"),
            password: yup.string().required("Пароль обязателен!"),
        });
        return {
            loading: false,
            message: "",
            schema,
        };
    },

    computed: {
        loggedIn() {
            return this.$store.state.auth.status.loggedIn;
        },
    },

    created() {
        if (this.loggedIn) {
            this.$router.push("/profile")
        }
    },

    methods: {
        handleLogin(user) {
            this.loading = true,
            this.$store.dispatch("auth/login", user)
            .then(() => {
                this.$router.push("/user");
            },
            (error) => {
                this.loading = false;
                this.message = 
                (error.response && 
                error.response.data &&
                error.response.data.message) ||
                error.message || error.toString();
            }
            );
        },
    },
};
</script>


<style scoped>
.main {
  display: flex;
  justify-content: center;
  align-items: center;
}

h3 {
  margin-bottom: 20px;
}

.form {
  border: 1px solid lightgray;
  border-radius: 3px;
  width: 25%;
  padding: 20px 20px 20px 20px;
}

.input {
  margin-bottom: 20px;
}
</style>