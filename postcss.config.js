module.exports = {
  plugins: {
    tailwindcss: {},
    autoprefixer: {},
    cssnano: process.env.NODE_ENV == 'production' ? {} : false
  },
  defaultExtractor: content => content.match(/[^<>"'.`\s]*[^<>"'.`\s:]/g),
}
