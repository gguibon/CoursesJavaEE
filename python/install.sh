wget --no-check-certificate https://bootstrap.pypa.io/get-pip.py -O - | python2.7 - --user

python2.7 get-pip.py

~/.local/bin/pip2.7 --user install "ipython[all]"
~/.local/bin/pip2.7 --user install "ipython[notebook]"
~/.local/bin/pip2.7 --user install pyzmq jinja2 tornado mistune jsonschema pygments terminado
~/.local/bin/pip2.7 install --user scikit-learn
~/.local/bin/pip2.7 install --user ipython
~/.local/bin/pip2.7 install --user scipy
~/.local/bin/pip2.7 install --user numpy
~/.local/bin/pip2.7 install --user lxml
~/.local/bin/pip2.7 install --user web.py

~/.local/bin/ipython

