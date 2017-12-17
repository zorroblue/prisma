from flask import Flask, render_template, jsonify, request
from PIL import Image
app = Flask(__name__)

@app.route('/upload', methods=['POST'])
def check_image():
    file = request.files['image']
    img = Image.open(file.stream)
    img.show()
    return jsonify(status='success')

@app.errorhandler(404)
def not_found(error):
    return render_template('404.html'), 404

if __name__ == '__main__':
    app.run(debug = True)
