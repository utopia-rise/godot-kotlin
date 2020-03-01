extends Spatial

export(String, "GdScript", "Kotlin") var language: String
export(int, 100, 10000, 100) var limit = 1000
export(int, 10, 200, 10) var size = 50
# Declare member variables here. Examples:
# var a = 2
# var b = "text"

var game = preload("res://game.tscn").instance()

# Called when the node enters the scene tree for the first time.
func _ready():
	if language == "GdScript":
		var script = load("res://game.gd")
		game.set_script(script)
	else:
		var script = load("res://game.gd")
		game.set_script(script)
	game.limit = limit
	game.size = size
	add_child(game)

# Called every frame. 'delta' is the elapsed time since the previous frame.
#func _process(delta):
#	pass
