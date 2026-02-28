/** @type {import('tailwindcss').Config} */
module.exports = {
  content: ["./src/**/*.{html,js}"],
  theme: {
    extend: {
      fontFamily : {
        "oswald" : "Oswald"
      },
      backgroundColor : {
        "main" : "#F2F2F2",
        "secondary-main" : "#BFBFBF"
      }
    },
  },
  plugins: [],
}

