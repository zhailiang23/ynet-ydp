/** @type {import('tailwindcss').Config} */
export default {
  darkMode: 'class',
  content: [
    "./index.html",
    "./src/**/*.{vue,js,ts,jsx,tsx}",
  ],
  theme: {
    extend: {
      colors: {
        primary: "#137fec",
        "primary-dark": "#0b66c3",
        "background-light": "#f6f7f8",
        "background-dark": "#101922",
        "surface-dark": "#1c252e",
        "surface-dark-highlight": "#233648",
        "text-secondary": "#92adc9",
      },
      fontFamily: {
        sans: ['-apple-system', 'BlinkMacSystemFont', 'Segoe UI', 'Roboto', 'Helvetica Neue', 'Arial', 'sans-serif'],
      },
      boxShadow: {
        'card': '0 2px 8px rgba(0, 0, 0, 0.08)',
        'card-dark': '0 2px 8px rgba(0, 0, 0, 0.3)',
      },
      borderRadius: {
        'card': '12px',
      },
    },
  },
  plugins: [],
}
