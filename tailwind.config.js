const defaultTheme = require('tailwindcss/defaultTheme')
const plugin = require('tailwindcss/plugin')

// in prod look at shadow-cljs output file in dev look at runtime, which will
// change files that are actually compiled; postcss watch should be a whole
// lot faster
const content = ["./node_modules/flyonui/dist/js/*.js"];
if (process.env.NODE_ENV == 'production') {
  content.push("./public/js/main.js");
} else {
  content.push(
    "./public/index.html",
    "./projects/frontend/src/**/.cljs",
    "./public/js/cljs-runtime/bankng.*.js",
  );
}

module.exports = {
  content,
  theme: {
    extend: {
      fontFamily: {
        sans: ["Open Sans", ...defaultTheme.fontFamily.sans],
        fancy: ["Amatic SC", "cursive"],
      },
      maxWidth: {
        'xs': '440px',
      },
      colors: {
        'dark-1': '#161926',
        'dark-2': '#1d2233',
        'dark-3': '#252a40',
        'dark-4': '#303753',
        'active': '#c1a657',
      },
    },
  },
  plugins: [
    require("flyonui"),
    require("flyonui/plugin"),
    plugin(function ({ addUtilities }) {
      const newUtilities = {
        '.safe-top': {
          paddingTop: 'constant(safe-area-inset-top)',
          paddingTop: 'env(safe-area-inset-top)'
        },
        '.safe-left': {
          paddingLeft: 'constant(safe-area-inset-left)',
          paddingLeft: 'env(safe-area-inset-left)'
        },
        '.safe-right': {
          paddingRight: 'constant(safe-area-inset-right)',
          paddingRight: 'env(safe-area-inset-right)'
        },
        '.safe-bottom': {
          paddingBottom: 'constant(safe-area-inset-bottom)',
          paddingBottom: 'env(safe-area-inset-bottom)'
        }
      }

      addUtilities(newUtilities);
    })
  ],
  flyonui: {
    themes: [{
      dark: {
        ...require("flyonui/src/theming/themes")["dark"],
        primary: "#c1a657",
      }
    }],
  },
}
