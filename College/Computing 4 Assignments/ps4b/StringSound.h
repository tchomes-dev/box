#include "CircularBuffer.h"
#include <vector>

class StringSound{ 
public:
    explicit StringSound (double frequency);
    explicit StringSound (vector<sf::Int16> init);
    StringSonnd(const StringSound &obj){};
    ~StringSonnd();
    void pluck();
    void tic();
    sf::Int16 sample();
    int time();
private:
    CircularBuffer* _cb;
    int _time;
}