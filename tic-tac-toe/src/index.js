import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';

    function Square(props) {
        return (
            <button className = {props.highlight === true ? "square1" : "square"}
            onClick={props.onClick}
            >
            {props.value}
            </button>
        );
    }
  
  class Board extends React.Component {
    
    renderSquare(i) {
        return (
          <Square
            highlight = {this.props.pos.includes(i) ? true : false}
            value={this.props.squares[i]}
            onClick={() => this.props.onClick(i)}
          />
        );
      }
  
    render() {
        const table = []
        for (let i = 0; i < 3; i++) {
            const row = []
            for (let j = 0; j < 3; j++) {
                 row.push(this.renderSquare(i*3 + j))
            }
            table.push(<div className="board-row">{row}</div>)
        }
        return (
            <div>
                {table}
            </div>
        )
        
    }
  }
  
  class Game extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
          history: [{
            squares: Array(9).fill(null),
            pos: -1,
          }],
          xIsNext: true,
          stepNumber: 0,
          pos: null
        };
      }

      handleClick(i) {
        const history = this.state.history.slice(0, this.state.stepNumber + 1);
        const current = history[history.length - 1];
        const squares = current.squares.slice();
        if (calculateWinner(squares) || squares[i]) {
          return;
        }
        squares[i] = this.state.xIsNext ? 'X' : 'O';
        this.setState({
          history: history.concat([{
            squares: squares,
            pos: i,
          }]),
          stepNumber: history.length,
          xIsNext: !this.state.xIsNext,
          
        });
      }

      jumpTo(step) {
        this.setState({
          stepNumber: step,
          xIsNext: (step % 2) === 0,
        });
      }

      render() {
        const history = this.state.history;
        const current = history[this.state.stepNumber];
        const winner = calculateWinner(current.squares);

        const moves = history.map((step, move) => {
            const desc = move ?
              'Go to move #' + move :
              'Go to game start';
            const row = step.pos >= 0 ? Math.floor(step.pos / 3) + 1 : "none"
            const col = step.pos >= 0 ? step.pos % 3 + 1 : "none"
            return (
              <li key={move}>
                <p>row: {row}, col: {col}</p>
                <button style = {this.state.stepNumber === move ? {fontWeight: "bold"} : null} onClick={() => this.jumpTo(move)}>{desc}</button>
              </li>
            );
          });
        
        let status;
        if (winner) {
          status = 'Winner: ' + winner.winner;
        } else {
            if(full(current.squares)){
                status = "Draw"
            } else {
                status = 'Next player: ' + (this.state.xIsNext ? 'X' : 'O');
            }
        }
    
        return (
          <div className="game">
            <div className="game-board">
              <Board
                squares={current.squares}
                onClick={(i) => this.handleClick(i)}
                pos = {winner ? winner.pos : [-1,-1,-1]}
              />
            </div>
            <div className="game-info">
              <div>{status}</div>
              <ol>{moves}</ol>
            </div>
          </div>
        );
      }
  }
  
  // ========================================
  
  ReactDOM.render(
    <Game />,
    document.getElementById('root')
  );

  function calculateWinner(squares) {
    const lines = [
      [0, 1, 2],
      [3, 4, 5],
      [6, 7, 8],
      [0, 3, 6],
      [1, 4, 7],
      [2, 5, 8],
      [0, 4, 8],
      [2, 4, 6],
    ];
    for (let i = 0; i < lines.length; i++) {
      const [a, b, c] = lines[i];
      if (squares[a] && squares[a] === squares[b] && squares[a] === squares[c]) {
        return {
            winner: squares[a],
            pos: lines[i],
        }
      }
    }
    return null;
  }
  function full(squares) {
    for (let i = 0; i < 9; i++) {
        if(squares[i]===null)
            return false
    }
    return true
  }
  