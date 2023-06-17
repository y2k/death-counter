.PHONY: run

run:
	clj -M --main cljs.main --repl-opts "{:launch-browser false}" --compile app.main --repl

publish:
	clj -M -m cljs.main -O advanced --compile app.main
