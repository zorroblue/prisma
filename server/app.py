from flask import Flask, render_template, jsonify, request
from PIL import Image
from neural_style.neural_style import stylize

app = Flask(__name__)
MODEL_DIR = 'saved-models/'

@app.route('/upload', methods=['POST'])
def check_image():
    file = request.files['image']
    img = Image.open(file.stream)
    output = stylize(img, model= MODEL_DIR+'udnie.pth')
    output.show()
    return jsonify(status='success')

@app.errorhandler(404)
def not_found(error):
    return render_template('404.html'), 404

if __name__ == '__main__':
    app.run(debug = True)
