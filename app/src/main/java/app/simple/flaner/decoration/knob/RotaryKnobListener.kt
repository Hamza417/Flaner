package app.simple.flaner.decoration.knob

interface RotaryKnobListener {
    fun onRotate(value: Float)
    fun onIncrement(value: Float)
}