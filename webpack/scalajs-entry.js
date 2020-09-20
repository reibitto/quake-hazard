if (process.env.NODE_ENV === "production") {
    const opt = require("./quake-hazard-opt.js");
    opt.main();
    module.exports = opt;
} else {
    var exports = window;
    exports.require = require("./quake-hazard-fastopt-entrypoint.js").require;
    window.global = window;

    const fastOpt = require("./quake-hazard-fastopt.js");
    fastOpt.main()
    module.exports = fastOpt;

    if (module.hot) {
        module.hot.accept();
    }
}
